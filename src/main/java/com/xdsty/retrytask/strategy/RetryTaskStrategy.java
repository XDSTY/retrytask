package com.xdsty.retrytask.strategy;

import org.springframework.beans.factory.InitializingBean;

public interface RetryTaskStrategy extends InitializingBean {

    /**
     * 执行任务
     * @param requestJson 任务json
     */
    void execute(String requestJson);

    /**
     * 返回任务类型
     * @return
     */
    String getType();

}
