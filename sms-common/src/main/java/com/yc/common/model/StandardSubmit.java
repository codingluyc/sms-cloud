package com.yc.common.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 策略模块需要做校验和封装的pojo类对象
 */
@Data
public class StandardSubmit {
    /**
     * 短信的唯一标识，雪花算法
     */
    private Long sequenceId;

    // 客户端标识，查询缓存
    private Long clientId;
    //api key
    private String apiKey;
    // 短信类型 0-验证码短信 1-通知类短信 2-营销类短信
    private Integer state;
    // ip白名单
    private String ip;
    //真实ip
    private String realIp;
    // uid
    private String uid;
    // 手机号
    private String mobile;
    // 签名
    private String signature;
    // 签名id
    private Long signId;
    // 短信内容
    private String text;
    //发送时间
    private LocalDateTime sendTime;
    // 当前短信的费用,计算短信文字的长度，按照70个字为一条，超出70个字，按照67个字为一条计算
    private Long fee;
    // 目标手机号的运营商,策略模块
    private Integer operatorId;
    // 目标手机号的归属地区号
    private Integer mobileRegionCode;
    // 目标手机号的归属地
    private String mobileLocation;
    // 通道下发的原号码
    private String channelOriginMobile;
    // 渠道商标识
    private Long channelId;
    // 短信的发送状态
    private Integer reportState;



}
