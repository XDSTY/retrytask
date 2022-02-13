package com.xdsty.retrytask.entity;

public class RetryBasicInfo {

    /**
     * 初始退避时间
     */
    private Integer backOffTime;

    /**
     * 指数退避指数
     */
    private Double exponentValue;

    /**
     * 指数退避最大退避时间
     */
    private Integer exponentMaxBackOffTime;

    /**
     * 最大重试次数
     */
    private Integer maxRetryCount;

    /**
     * 请求参数json
     */
    private String requestJson;

    public Integer getBackOffTime() {
        return backOffTime;
    }

    public void setBackOffTime(Integer backOffTime) {
        this.backOffTime = backOffTime;
    }

    public Double getExponentValue() {
        return exponentValue;
    }

    public void setExponentValue(Double exponentValue) {
        this.exponentValue = exponentValue;
    }

    public Integer getExponentMaxBackOffTime() {
        return exponentMaxBackOffTime;
    }

    public void setExponentMaxBackOffTime(Integer exponentMaxBackOffTime) {
        this.exponentMaxBackOffTime = exponentMaxBackOffTime;
    }

    public Integer getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(Integer maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }
}
