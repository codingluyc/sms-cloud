package com.yc.stratergy.listener;

import com.rabbitmq.client.Channel;
import com.yc.common.constants.RabbitMQConstants;
import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 发送前消息监听
 */
@Component
@Slf4j
public class PreSendListener {

    @RabbitListener(queues = RabbitMQConstants.PRE_SEND_QUEUE)
    public void preSend(StandardSubmit submit, Message message, Channel channel) throws IOException {
        log.info("msg: {} " ,submit);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
