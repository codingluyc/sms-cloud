package com.yc.api.common;

/**
 * | 0      | 代表接收成功，短信发送ing…… |
 *      * | -1     | 非法的apikey                  |
 *      * | -2     | 请求的ip不在白名单内          |
 *      * | -3     | 无可用签名                    |
 *      * | -4     | 无可用模板                    |
 *      * | -5     | 手机号格式不正确              |
 *      * | -6     | 客户余额不足                  |
 *      * | -7     | 短信内容中存在非法字符        |
 *      * | -8     | 短信内容中存在敏感词          |
 *      * | -9     | 签名内容中存在非法字符        |
 *      * | -10   | 模板内容中存在非法字符         |
 *      * | -11   | 模板参数个数不匹配             |
 *
 */
public enum SmsCodeEnum {
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    ERROR(2, "错误"),
    INVALID_APIKEY(-1, "非法的apikey"),
    IP_NOT_IN_WHITE_LIST(-2, "请求的ip不在白名单内"),
    NO_SIGNATURE(-3, "无可用签名"),
    NO_TEMPLATE(-4, "无可用模板"),
    INVALID_PHONE_NUMBER(-5, "手机号格式不正确"),
    INSUFFICIENT_BALANCE(-6, "客户余额不足"),
    ;
    private Integer code;
    private String message;
    SmsCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public static String getMessage(Integer code) {
        for (SmsCodeEnum value : SmsCodeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value.getMessage();
            }
        }
        return null;
    }

}
