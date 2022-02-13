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

    /**
     * 任务类型
     */
    private String taskType;

    public Integer getFixBackOffTime() {
        return fixBackOffTime;
    }

    public void setFixBackOffTime(Integer fixBackOffTime) {
        this.fixBackOffTime = fixBackOffTime;
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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
