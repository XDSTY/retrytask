package com.xdsty.retrytask.vo;

public class FixBackOffRetryTask {

    /**
     * 固定的退避时间
     */
    private Integer fixBackOffTime;

    /**
     * 最大的重试次数
     */
    private Integer maxRetryCount;

    /**
     * 请求json
     */
    private String requestJson;

}
