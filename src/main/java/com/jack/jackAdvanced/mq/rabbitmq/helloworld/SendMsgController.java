package com.jack.jackAdvanced.mq.rabbitmq.helloworld;

import com.jack.jackAdvanced.result.Result;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/send")
public class SendMsgController {

    @Autowired
    private MessageProvider messageProvider;

    /**
     * 主要测试一个死信队列，功能主要实现延时消费，原理是先把消息发到正常队列，
     * 正常队列有超时时间，当达到时间后自动发到死信队列，然后由消费者去消费死信队列里的消息.
     */
    @RequestMapping("/sendMessage")
    public Object sendMessage(){
        MessagePojo pojo = new MessagePojo();
        pojo.setClassName("MessagePojo");
        pojo.setMessageId(UUID.randomUUID().toString());
        pojo.setCreateTime(new Date());
        pojo.setDelay(10);
        //发送消息
        messageProvider.sendMessage(pojo);
        return pojo;
    }

}
