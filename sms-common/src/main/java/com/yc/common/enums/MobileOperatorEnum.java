package com.yc.common.enums;

/**
 * 短信运营商枚举
 */
public enum MobileOperatorEnum {
    ChinaMobile("移动", 1),
    ChinaUnicom("中联通", 2),
    ChinaTelecom("电信", 3),
    Other("其他", 4),
    Unknown("未知", 5),
    ;
    private String name;
    private Integer code;

    MobileOperatorEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public Integer getCode() {
        return code;
    }

    public static MobileOperatorEnum getByCode(Integer code) {
        for (MobileOperatorEnum mobileOperatorEnum : MobileOperatorEnum.values()) {
            if (mobileOperatorEnum.getCode().equals(code)) {
                return mobileOperatorEnum;
            }
        }
        return null;
    }
    public static MobileOperatorEnum getByName(String name) {
        for (MobileOperatorEnum mobileOperatorEnum : MobileOperatorEnum.values()) {
            if (mobileOperatorEnum.getName().equals(name)) {
                return mobileOperatorEnum;
            }
        }
        return null;
    }
}
