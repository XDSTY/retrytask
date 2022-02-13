package com.xdsty.retrytask.servcie;

import com.xdsty.retrytask.vo.ExponentBackOffRetryTask;
import com.xdsty.retrytask.vo.FixBackOffRetryTask;

import java.sql.SQLException;

public interface RetryTaskService {

    /**
     * 提交固定时间重试任务 将任务插入到数据库，请在事务中调用该方法
     */
    void submitFixBackOffTask(FixBackOffRetryTask task) throws SQLException;

    /**
     * 提交指数退避重试任务 将任务插入到数据库，请在事务中调用该方法
     */
    void submitExponentTask(ExponentBackOffRetryTask task);

}
