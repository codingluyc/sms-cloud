package com.yc.stratergy.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @description: 配置类
 */
@SpringBootConfiguration
@Slf4j
public class RabbitMQTemplateConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        /**
         * 确认消息是否到达Broker
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(!ack){
                    log.error("消息发送失败,{}",cause);
                }
            }
        });

        /**
         * 确认消息是否路由到指定的queue
         */
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.error("消息发送失败,message:{} , excahnge:{} routingKey",new String(message.getBody()),exchange,routingKey);
            }
        });

        return rabbitTemplate;

    }
}
