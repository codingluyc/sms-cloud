package com.yc.common.enums;

/**
 * 短信状态枚举
 */
public enum ReportEnum {
    SUCCESS(1, "成功"),
    FAIL(2, "失败"),
    UNKNOWN(3, "未知");
    private Integer code;
    private String msg;
    ReportEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
