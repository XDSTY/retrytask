package com.xdsty.retrytask.vo;

public class ExponentBackOffRetryTask {

    /**
     * 第一次退避时间
     */
    private Integer backOffTime;

    /**
     * 退避的指数值 当前的退避时间 = 上次的退避时间 * (1 + exponentValue)
     */
    private Double exponentValue;

    /**
     * 最大的重试次数
     */
    private Integer maxRetryCount;

    /**
     * 最大的退避时间 防止退避时间过大
     */
    private Integer exponentMaxBackOffTime;

    /**
     * 请求参数
     */
    private String requestJson;

    /**
     * 任务类型
     */
    private String taskType;
}
