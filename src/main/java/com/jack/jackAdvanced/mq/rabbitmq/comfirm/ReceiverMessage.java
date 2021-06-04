package com.jack.jackAdvanced.mq.rabbitmq.comfirm;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *  消息接收确认
 */
@Slf4j
@Component
@RabbitListener(queues = "confirm_test_queue")
public class ReceiverMessage {

    @RabbitHandler
    public void processHandler(String msg, Channel channel, Message message) throws IOException {

        try {
            log.info("小富收到消息：{}", msg);

            // TODO 具体业务

            // basicAck：表示成功确认，使用此回执方法后，消息会被rabbitmq broker 删除。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }  catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败,拒绝再次接收...");
                // basicReject：拒绝消息，与basicNack区别在于不能进行批量操作，其他用法很相似。
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                log.error("消息即将再次返回队列处理...");
                /**
                 * basicNack表示失败确认，一般在消费消息业务异常时用到此方法，可以将消息重新投递入队列
                 * 参数说明
                 *  deliveryTag：表示消息投递序号
                 *  multiple：是否批量确认
                 *  requeue：值为 true 消息将重新入队列
                 */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
