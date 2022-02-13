package com.xdsty.retrytask.thread;

import com.xdsty.retrytask.vo.RetryTaskContext;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class RetryTaskRunnable implements Runnable {

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
        // 设置任务为执行中
    }
}
