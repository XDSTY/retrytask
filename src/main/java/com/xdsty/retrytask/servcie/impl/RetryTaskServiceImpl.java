package com.xdsty.retrytask.servcie.impl;

import com.xdsty.retrytask.constant.BackOffTypeEnum;
import com.xdsty.retrytask.constant.RetrySQLConstant;
import com.xdsty.retrytask.constant.RetryTaskStatusEnum;
import com.xdsty.retrytask.constant.RetryTypeEnum;
import com.xdsty.retrytask.entity.RetryBasicInfo;
import com.xdsty.retrytask.entity.RetryTask;
import com.xdsty.retrytask.policy.BackOffPolicy;
import com.xdsty.retrytask.policy.BackOffPolicyFactory;
import com.xdsty.retrytask.servcie.RetryTaskService;
import com.xdsty.retrytask.strategy.RetryTaskStrategy;
import com.xdsty.retrytask.strategy.RetryTaskStrategyFactory;
import com.xdsty.retrytask.thread.RetryTaskRunnable;
import com.xdsty.retrytask.util.JsonUtil;
import com.xdsty.retrytask.util.RetryTaskException;
import com.xdsty.retrytask.vo.ExponentBackOffRetryTask;
import com.xdsty.retrytask.vo.FixBackOffRetryTask;
import com.xdsty.retrytask.vo.RetryTaskContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class RetryTaskServiceImpl implements RetryTaskService {

    private static final Logger log = LoggerFactory.getLogger(RetryTaskService.class);

    private ScheduledThreadPoolExecutor poolExecutor;

    private DataSource dataSource;

    public RetryTaskServiceImpl(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, DataSource dataSource) {
        this.poolExecutor = scheduledThreadPoolExecutor;
        this.dataSource = dataSource;
    }

    @Override
    public void submitFixBackOffTask(FixBackOffRetryTask task) throws SQLException {
        RetryTask retryTask = buildRetryTask(task);
        submitTask(retryTask);
    }

    @Override
    public void submitExponentTask(ExponentBackOffRetryTask task) {
        RetryTask retryTask = buildRetryTask(task);
        submitTask(retryTask);
    }

    private void submitTask(RetryTask retryTask) {
        // 如果当前存在事务，则获取当前事务的connection；当前不存在事务则新建一个connection
        Connection connection = DataSourceUtils.getConnection(dataSource);

        long id = insertTask(retryTask, connection);
        retryTask.setId(id);

        RetryTaskContext retryTaskContext = buildRetryTaskContext(retryTask);
        RetryTaskRunnable retryTaskRunnable = buildRetryTaskRunnable(poolExecutor, retryTaskContext);
        // 当前事务执行完成后将该任务提交到线程池
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                poolExecutor.execute(retryTaskRunnable);
            }
        });
    }

    private long insertTask(RetryTask retryTask, Connection connection) {
        // 将任务插入到数据库
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(RetrySQLConstant.INSERT_TASK);
            statement.setString(1, retryTask.getTaskType());
            statement.setString(2, retryTask.getRetryBasicInfoJson());
            statement.setInt(3, retryTask.getBackOffType());
            statement.setInt(4, retryTask.getRetryType());
            statement.setInt(5, retryTask.getTaskStatus());
            statement.setInt(6, retryTask.getRetryCount());
            statement.setString(7, retryTask.getCreateUser());
            if (!statement.execute()) {
                throw new RetryTaskException("异步任务提交失败");
            }
            // 获取任务的主键id
            ResultSet resultSet = statement.getGeneratedKeys();
            long id = 0;
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            log.error("新增任务失败, taskInfo: {}", JsonUtil.getJsonStr(retryTask), e);
            throw new RetryTaskException("新增任务失败");
        }
    }

    /**
     * 构建执行任务
     * @param executor
     * @param retryTaskContext
     * @return
     */
    private RetryTaskRunnable buildRetryTaskRunnable(ScheduledThreadPoolExecutor executor, RetryTaskContext retryTaskContext) {
        return new RetryTaskRunnable(executor, retryTaskContext);
    }

    /**
     * 构建异步任务
     * @param fixBackOffRetryTask
     * @return
     */
    private RetryTask buildRetryTask(FixBackOffRetryTask fixBackOffRetryTask) {
        RetryBasicInfo basicInfo = new RetryBasicInfo();
        basicInfo.setBackOffTime(fixBackOffRetryTask.getFixBackOffTime());
        basicInfo.setMaxRetryCount(fixBackOffRetryTask.getMaxRetryCount());
        basicInfo.setRequestJson(fixBackOffRetryTask.getRequestJson());

        return buildRetryTask(basicInfo, BackOffTypeEnum.FIX_BACK_OFF, fixBackOffRetryTask.getTaskType());
    }

    private RetryTask buildRetryTask(ExponentBackOffRetryTask exponentBackOffRetryTask) {
        RetryBasicInfo basicInfo = new RetryBasicInfo();
        basicInfo.setBackOffTime(exponentBackOffRetryTask.getBackOffTime());
        basicInfo.setMaxRetryCount(exponentBackOffRetryTask.getMaxRetryCount());
        basicInfo.setRequestJson(exponentBackOffRetryTask.getRequestJson());
        basicInfo.setExponentValue(exponentBackOffRetryTask.getExponentValue());
        basicInfo.setExponentMaxBackOffTime(exponentBackOffRetryTask.getExponentMaxBackOffTime());

        return buildRetryTask(basicInfo, BackOffTypeEnum.EXPONENT_BACK_OFF, exponentBackOffRetryTask.getTaskType());
    }

    private RetryTask buildRetryTask(RetryBasicInfo basicInfo, BackOffTypeEnum backOffTypeEnum, String taskType) {
        RetryTask retryTask = new RetryTask();
        retryTask.setTaskType(taskType);
        retryTask.setRetryBasicInfoJson(JsonUtil.getJsonStr(basicInfo));
        retryTask.setBackOffType(backOffTypeEnum.getCode());
        retryTask.setRetryType(RetryTypeEnum.EXCEPTION_RETRY_TYPE.getCode());
        retryTask.setTaskStatus(RetryTaskStatusEnum.INIT.getCode());
        retryTask.setRetryCount(0);
        return retryTask;
    }

    /**
     * 构建 异步任务执行上下文
     * @param retryTask
     * @return
     */
    private RetryTaskContext buildRetryTaskContext(RetryTask retryTask) {
        RetryTaskStrategy strategy = buildRetryTaskStrategy(retryTask.getTaskType());
        BackOffPolicy backOffPolicy = buildBackOffPolicy(retryTask.getBackOffType());
        return new RetryTaskContext(retryTask, strategy, backOffPolicy, dataSource);
    }

    private RetryTaskStrategy buildRetryTaskStrategy(String taskType) {
        return RetryTaskStrategyFactory.getRetryStrategy(taskType);
    }

    /**
     * 构建退避策略
     * @param backOffType
     * @return
     */
    private BackOffPolicy buildBackOffPolicy(Integer backOffType) {
        return BackOffPolicyFactory.getBackOffPolicy(backOffType);
    }


}
