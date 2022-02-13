package com.xdsty.retrytask.policy;

import com.xdsty.retrytask.vo.RetryTaskContext;

public interface BackOffPolicy {

    /**
     * 返回类型
     * @return
     */
    Integer getType();

    /**
     * 是否可以重试
     * @param retryTaskContext
     * @return
     */
    boolean canRetry(RetryTaskContext retryTaskContext);

    /**
     * 下次重试的时间
     * @param retryTaskContext
     * @return
     */
    Integer getNextRetryInterval(RetryTaskContext retryTaskContext);

}
