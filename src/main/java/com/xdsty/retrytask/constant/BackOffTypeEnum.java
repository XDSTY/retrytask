package com.xdsty.retrytask.constant;

public enum BackOffTypeEnum {
    FIX_BACK_OFF(0, "FIX_BACK_OFF"),
    EXPONENT_BACK_OFF(1, "EXPONENT_BACK_OFF")
    ;

    private Integer code;

    private String desc;

    BackOffTypeEnum(Integer code, String desc) {
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
