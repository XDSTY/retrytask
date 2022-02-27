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

    /**
     * 创建人
     */
    private String createUser;

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

    public Integer getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(Integer maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public Integer getExponentMaxBackOffTime() {
        return exponentMaxBackOffTime;
    }

    public void setExponentMaxBackOffTime(Integer exponentMaxBackOffTime) {
        this.exponentMaxBackOffTime = exponentMaxBackOffTime;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
