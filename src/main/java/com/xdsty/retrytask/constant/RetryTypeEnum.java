package com.xdsty.retrytask.constant;

public enum RetryTypeEnum {

    EXCEPTION_RETRY_TYPE(0, "出现异常重试")
    ;

    private Integer code;

    private String desc;

    RetryTypeEnum(Integer code, String desc) {
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
