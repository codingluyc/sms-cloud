package com.yc.common.enums;

/**
 * @description: 错误枚举
 *      * | 状态码 | 说明                          |
 *      * | ------ | ----------------------------- |
 *      * | 0      | 代表接收成功，短信发送ing…… |
 *      * | -1     | 非法的apikey                  |
 *      * | -2     | 请求的ip不在白名单内          |
 *      * | -3     | 无可用签名                    |
 *      * | -4     | 无可用模板                    |
 *      * | -5     | 手机号格式不正确              |
 *      * | -6     | 客户余额不足                  |
 */
public enum ExceptionEnums {
    ILLEGAL_APIKEY(-1, "非法的apikey"),
    IP_NOT_IN_WHITE_LIST(-2, "请求的ip不在白名单内"),
    NO_SIGN(-3, "无可用签名"),
    NO_TEMPLATE(-4, "无可用模板"),
    PHONE_FORMAT_ERROR(-5, "手机号格式不正确"),
    CLIENT_BALANCE_NOT_ENOUGH(-6, "客户余额不足"),
    SNOWFLAKE_OUT_OF_RANGE(-7, "snowflake超出范围"),
    SNOWFLAKE_TIME_BACK(-8, "snowflake时间回拨"),


    DIRTY_WORD(-9, "包含敏感词"),
    MOBILE_BLACK_CLIENT(-10,"用户级手机号黑名单" ),
    MOBILE_BLACK_GLOBAL(-10,"平台级手机号黑名单" ),
    MOBILE_OPERATOR_NOT_FOUND(-11,"手机运营商未找到"),

    LIMIT_MINUTES(-12,"一分钟内发送次数超过限制"),
    LIMIT_HOURS(-13,"一小时内发送次数超过限制"),
    ;
    private Integer code;
    private String msg;
    ExceptionEnums(Integer code, String msg) {
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
