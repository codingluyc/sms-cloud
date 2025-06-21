package com.yc.stratergy.listener;

import com.rabbitmq.client.Channel;
import com.yc.common.constants.RabbitMQConstants;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.filter.StrategyFilterContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 发送前消息监听
 */
@Component
@Slf4j
public class PreSendListener {

    @Autowired
    private StrategyFilterContext strategyFilterContext;

    @RabbitListener(queues = RabbitMQConstants.PRE_SEND_QUEUE)
    public void preSend(StandardSubmit submit, Message message, Channel channel) throws IOException {
        log.info("msg: {} " ,submit);
        try {
            strategyFilterContext.strategy(submit);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            log.error("策略执行异常: {}",e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }


    }
}
