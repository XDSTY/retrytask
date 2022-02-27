package com.xdsty.retrytask.vo;

import com.xdsty.retrytask.entity.RetryTask;
import com.xdsty.retrytask.policy.BackOffPolicy;
import com.xdsty.retrytask.strategy.RetryTaskStrategy;

import javax.sql.DataSource;

public class RetryTaskContext {

    private RetryTask retryTask;

    private RetryTaskStrategy retryTaskStrategy;

    /**
     * 已经重试的次数
     */
    private Integer retryCount;

    /**
     * 异常
     */
    private Throwable throwable;

    /**
     * 退避策略
     */
    private BackOffPolicy backOffPolicy;

    /**
     * 连接池
     */
    private DataSource dataSource;

    public RetryTaskContext() {
    }

    public RetryTaskContext(RetryTask retryTask, RetryTaskStrategy retryTaskStrategy, BackOffPolicy backOffPolicy, DataSource dataSource) {
        this.retryTask = retryTask;
        this.retryTaskStrategy = retryTaskStrategy;
        this.backOffPolicy = backOffPolicy;
        this.dataSource = dataSource;
    }

    public RetryTask getRetryTask() {
        return retryTask;
    }

    public RetryTaskStrategy getRetryTaskStrategy() {
        return retryTaskStrategy;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public BackOffPolicy getBackOffPolicy() {
        return backOffPolicy;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 捕获异常
     * @param e
     */
    public void registerException(Throwable e) {
        this.throwable = e;
        this.retryCount ++;
    }
}
