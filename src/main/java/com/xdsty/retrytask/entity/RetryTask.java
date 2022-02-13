package com.xdsty.retrytask.entity;

public class RetryTask {

    /**
     * 主键
     */
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务基本信息json
     */
    private String retryBasicInfoJson;

    /**
     * 任务基本信息
     */
    private RetryBasicInfo retryBasicInfo;

    /**
     * 退避策略
     */
    private Integer backOffType;

    /**
     * 重试策略
     */
    private Integer retryType;

    /**
     * 任务状态
     */
    private Integer taskStatus;

    /**
     * 重试的次数
     */
    private Integer retryCount;

    /**
     * 最终失败时的异常信息
     */
    private String exceptionMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getRetryBasicInfoJson() {
        return retryBasicInfoJson;
    }

    public void setRetryBasicInfoJson(String retryBasicInfoJson) {
        this.retryBasicInfoJson = retryBasicInfoJson;
    }

    public RetryBasicInfo getRetryBasicInfo() {
        return retryBasicInfo;
    }

    public void setRetryBasicInfo(RetryBasicInfo retryBasicInfo) {
        this.retryBasicInfo = retryBasicInfo;
    }

    public Integer getBackOffType() {
        return backOffType;
    }

    public void setBackOffType(Integer backOffType) {
        this.backOffType = backOffType;
    }

    public Integer getRetryType() {
        return retryType;
    }

    public void setRetryType(Integer retryType) {
        this.retryType = retryType;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
