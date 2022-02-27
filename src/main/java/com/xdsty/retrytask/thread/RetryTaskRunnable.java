package com.xdsty.retrytask.thread;

import com.xdsty.retrytask.constant.RetrySQLConstant;
import com.xdsty.retrytask.constant.RetryTaskStatusEnum;
import com.xdsty.retrytask.entity.RetryBasicInfo;
import com.xdsty.retrytask.entity.RetryTask;
import com.xdsty.retrytask.policy.BackOffPolicy;
import com.xdsty.retrytask.strategy.RetryTaskStrategy;
import com.xdsty.retrytask.util.JsonUtil;
import com.xdsty.retrytask.util.RetryTaskException;
import com.xdsty.retrytask.vo.RetryTaskContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RetryTaskRunnable implements Runnable {

    private static Logger log = LoggerFactory.getLogger(RetryTaskRunnable.class);

    private ScheduledThreadPoolExecutor executor;

    private RetryTaskContext retryTaskContext;

    public RetryTaskRunnable() {
    }

    public RetryTaskRunnable(ScheduledThreadPoolExecutor executor, RetryTaskContext retryTaskContext) {
        this.executor = executor;
        this.retryTaskContext = retryTaskContext;
    }

    @Override
    public void run() {
        RetryTask retryTask = retryTaskContext.getRetryTask();
        RetryBasicInfo retryBasicInfo = retryTask.getRetryBasicInfo();
        log.info("task start: {}", JsonUtil.getJsonStr(retryTask));

        // 设置任务为执行中
        Connection connection = DataSourceUtils.getConnection(retryTaskContext.getDataSource());
        updateTaskStart(retryTask, connection);

        // 执行任务
        try {
            RetryTaskStrategy retryTaskStrategy = retryTaskContext.getRetryTaskStrategy();
            retryTaskStrategy.execute(retryBasicInfo.getRequestJson());
        } catch (Exception e) {
            // 执行失败
            retryTaskContext.registerException(e);
            BackOffPolicy backOffPolicy = retryTaskContext.getBackOffPolicy();
            if (!backOffPolicy.canRetry(retryTaskContext)) {
                log.info("task final fail, task: {}", JsonUtil.getJsonStr(retryTask));
                // 不可以再重试，更新任务为失败状态
                updateTaskFinish(retryTask, connection, RetryTaskStatusEnum.FAIL);
                return;
            }
            // 还可以重试，获取重试时间后重试
            Integer intervalTime = backOffPolicy.getNextRetryInterval(retryTaskContext);
            executor.schedule(this, intervalTime, TimeUnit.SECONDS);
        }
        // 执行成功
        updateTaskFinish(retryTask, connection, RetryTaskStatusEnum.SUCCESS);
    }

    private void updateTaskStart(RetryTask retryTask, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(RetrySQLConstant.TASK_START);
            Date startTime = new Date(System.currentTimeMillis());
            statement.setDate(1, startTime);
            statement.setInt(2, RetryTaskStatusEnum.PROCESSING.getCode());
            statement.setLong(3, retryTask.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("更新异步任务状态失败, taskInfo: {}", JsonUtil.getJsonStr(retryTask));
            throw new RetryTaskException("更新异步任务状态失败");
        }
    }

    private void updateTaskFinish(RetryTask retryTask, Connection connection, RetryTaskStatusEnum statusEnum) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(RetrySQLConstant.TASK_FAIL);
            Date finishTime = new Date(System.currentTimeMillis());
            statement.setDate(1, finishTime);
            statement.setInt(2, statusEnum.getCode());
            statement.setLong(3, retryTask.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("更新异步任务状态失败, taskInfo: {}", JsonUtil.getJsonStr(retryTask));
            throw new RetryTaskException("更新异步任务状态失败");
        }
    }
}
