package com.yc.common.constants;

/**
 * @description: RabbitMQ常量
 */
public interface RabbitMQConstants {

    // 预发送队列-接口模块通知策略模块
    String PRE_SEND_QUEUE = "pre_send_queue";

    // 手机号码归属地队列-通知web
    String MOBILE_AREA_OPERATOR = "mobile_area_operator";

    // 策略模块通知日志存储
    String SMS_WRITE_LOG = "sms_write_log";

    // 策略模块通知回调推送
    String SMS_PUSH_REPORT = "sms_push_report";
}
