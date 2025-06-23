package com.yc.stratergy.util;

/**
 * 费用工具
 */
public class ClientBalanceUtil {

    /**
     * 获取用户可透支余额
     * @param clientId
     * @return
     */
    public static Long getClientAmountLimit(Long clientId){
        //20元，单位厘
        return -200000L;
    }
}
