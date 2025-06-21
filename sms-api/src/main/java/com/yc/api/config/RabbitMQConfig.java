package com.yc.api.config;

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

    @Bean
    public Queue preSendQueue(){
        return QueueBuilder.durable(RabbitMQConstants.PRE_SEND_QUEUE).build();
    }
}
