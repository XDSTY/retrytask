package com.xdsty.retrytask.constant;

public enum RetryTaskStatusEnum {

    INIT(0, "初始化"),
    PROCESSING(1, "执行中"),
    SUCCESS(2, "成功"),
    FAIL(3, "失败")
    ;

    private Integer code;

    private String desc;

    RetryTaskStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
