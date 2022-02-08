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
}
