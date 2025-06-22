package com.yc.stratergy.config;

import com.yc.common.constants.RabbitMQConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;



/**
 * @description: Rabbit MQ配置类
 */
@SpringBootConfiguration
public class RabbitMQConfig {

    // 预发送状态队列
    @Bean
    public Queue preSendQueue(){
        return QueueBuilder.durable(RabbitMQConstants.MOBILE_AREA_OPERATOR).build();
    }


    // 日志读写队列
    @Bean
    public Queue smsWriteLogQueue(){
        return QueueBuilder.durable(RabbitMQConstants.SMS_WRITE_LOG).build();
    }

    // 日志读写队列
    @Bean
    public Queue smsPushReportQueue(){
        return QueueBuilder.durable(RabbitMQConstants.SMS_PUSH_REPORT).build();
    }
}
