package com.jack.jackAdvanced.mq.rabbitmq.helloworld;


import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;

import static com.jack.jackAdvanced.mq.rabbitmq.helloworld.RabbitConfig.ORDER_DLX_QUEUE;

@Component
@RabbitListener(queues = ORDER_DLX_QUEUE)//监听的是延时队列
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitHandler
    public void handler(String msg, Channel channel, Message message) throws IOException {

        if(!StringUtils.isEmpty(msg)){
            MessagePojo messagePojo = JSONUtil.toBean(msg, MessagePojo.class);
            try {
                //basicAck(deliveryTag,multiple)
                //deliveryTag：唯一标识ID
                //multiple：是否批量处理.true:将一次性ack所有小于deliveryTag的消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

                logger.info("消费的消息: " + JSONUtil.toJsonStr(messagePojo));
                logger.info("消费消息时间： " + new Date());
            } catch (IOException e) {
                logger.error("确认消费异常",e);
            }
        }
    }
}
