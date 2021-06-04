package com.jack.jackAdvanced.mq.rabbitmq.comfirm;

import com.jack.jackAdvanced.result.Result;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 *  消息发送
 */
@RestController
@RequestMapping("/rabbitmq")
public class MessageSendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ConfirmCallbackService confirmCallbackService;
    @Autowired
    private ReturnCallbackService returnCallbackService;

    @RequestMapping("/send")
    public Result sendMsg(){
        this.sendMessage("confirmTestExchange","confirmTestQueue","hello rabbitmq....");
        return Result.success("send message success..");
    }

    public void sendMessage(String exchange, String routingKey, Object msg) {
        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        rabbitTemplate.setMandatory(true);

        /**
         * 消费者确认收到消息后，手动ack回执回调处理
         */
        rabbitTemplate.setConfirmCallback(confirmCallbackService);

        /**
         * 消息投递到队列失败回调处理
         */
        rabbitTemplate.setReturnCallback(returnCallbackService);

        /**
         * 发送消息
         */
        rabbitTemplate.convertAndSend(exchange, routingKey, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }
}
