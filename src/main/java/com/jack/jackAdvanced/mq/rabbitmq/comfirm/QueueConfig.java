package com.jack.jackAdvanced.mq.rabbitmq.comfirm;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  定义交换机 confirmTestExchange 和队列 confirm_test_queue ，并将队列绑定在交换机上。
 *  rabbitmq 的消息确认分为两部分：发送消息确认 和 消息接收确认。
 */
@Configuration
public class QueueConfig {

    @Bean(name = "confirmTestQueue")
    public Queue confirmTestQueue() {
        return new Queue("confirm_test_queue", true, false, false);
    }

    @Bean(name = "confirmTestExchange")
    public FanoutExchange confirmTestExchange() {
        return new FanoutExchange("confirmTestExchange");
    }

    @Bean
    public Binding confirmTestFanoutExchangeAndQueue(
            @Qualifier("confirmTestExchange") FanoutExchange confirmTestExchange,
            @Qualifier("confirmTestQueue") Queue confirmTestQueue) {
        return BindingBuilder.bind(confirmTestQueue).to(confirmTestExchange);
    }

}
