package com.xdsty.retrytask.entity;

import java.util.Date;

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

    /**
     * 任务创建时间
     */
    private Date createTime;

    /**
     * 创建人姓名
     */
    private String createUser;

    /**
     * 开始执行时间
     */
    private Date startExecuteTime;

    /**
     * 最终完成或者失败时间
     */
    private Date finishTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getStartExecuteTime() {
        return startExecuteTime;
    }

    public void setStartExecuteTime(Date startExecuteTime) {
        this.startExecuteTime = startExecuteTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
