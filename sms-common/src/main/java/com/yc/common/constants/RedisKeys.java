package com.yc.common.constants;

/**
 * @description: redis key
 */
public class RedisKeys {
    //用户信息
    public static final String CLIENT_BUSINESS = "client_business:";

    //用户信息中的自定义过滤器
    public static final String CLIENT_FILTERS = "clientFilters";
    //签名
    public static final String CLIENT_SIGN = "client_sign:";

    //模板
    public static final String CLIENT_TEMPLATE = "client_template:";

    //余额
    public static final String CLIENT_BALANCE = "client_balance:";
    //余额字段
    public static final String FEE_FIELD = "balance";

    //手机号段
    public static final String PHASE = "phase:";

    //敏感词
    public static final String MOBILE_DIRTYWORD = "mobile_dirtyword";


    //手机号黑名单
    public static final String MOBILE_BLACK = "black:";

    //携号转网
    public static final String TRANSFER = "transfer";

    //分钟限流
    public static final String LIMIT = "limit:minute:";

    //小时限流
    public static final String LIMIT_HOUR = "limit:hour:";
    //用户通道
    public static final String CLIENT_CHANNEL = "clientChannel:";

    //通道
    public static final String CHANNEL = "channel:";
}
