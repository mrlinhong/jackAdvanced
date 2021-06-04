package com.jack.jackAdvanced.mq.rabbitmq.helloworld;

import cn.hutool.json.JSONUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.jack.jackAdvanced.mq.rabbitmq.helloworld.RabbitConfig.*;

/**
 * 那么就单单实现在提交订单后的15分钟内，如果没有完成支付，系统关闭订单。有哪些可行的方案呢。
 * 方案:
 * 使用定时任务轮询订单表，查询出订单创建了15分钟以上并且未支付的订单，如果有查询出此类订单则执行关闭。
 *
 * 缺点：假设每1分钟轮询一次，则会存在秒级误差，如果秒级轮询，则会极其消耗性能，影响程序的健壮性。
 * 提交订单时开启一个新线程，而新线程直接休眠15分钟，休眠结束后开始执行订单关闭
 *
 * 缺点：如果在线程休眠时，重启了整个服务，那么会怎样呢？
 * 使用延时消息队列
 *
 * 缺点：需要额外部署消息中间件
 */
@Component
public class MessageProvider {

    private RabbitTemplate rabbitTemplate;

    /**
     * 模板消息实现类
     * @param rabbitTemplate
     */
    public MessageProvider(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMandatory(true);
    }

    public void sendMessage(MessagePojo messagePojo){
        if(messagePojo != null){
            String msg = JSONUtil.toJsonStr(messagePojo);

            //执行发送消息到指定队列
            CorrelationData correlationData = new CorrelationData(messagePojo.getMessageId());

            rabbitTemplate.convertAndSend(ORDER_TTL_EXCHANGE,ORDER_TTL_ROUTINGKEY,msg,message -> {
                //设置延时超时时间
                message.getMessageProperties().setExpiration(String.valueOf(messagePojo.getDelay() * 1000));
                return message;
            },correlationData);
            System.out.println("消息发送时间: "+new Date());
        }else{
            System.out.println("消息内容为空");
        }
    }
}
