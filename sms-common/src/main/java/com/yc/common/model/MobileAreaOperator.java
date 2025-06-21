package com.yc.common.model;

import lombok.Data;

/**
 * 手机号信息
 */
@Data
public class MobileAreaOperator {
    private String mobile;
    private String areaInfo;

    public MobileAreaOperator() {
    }

    public MobileAreaOperator(String mobile, String areaInfo) {
        this.mobile = mobile;
        this.areaInfo = areaInfo;
    }
}
