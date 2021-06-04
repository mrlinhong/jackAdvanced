package com.jack.jackAdvanced.mq.rabbitmq.helloworld;

import lombok.Data;

import java.util.Date;

@Data
public class MessagePojo {
    private String messageId;
    private String className;
    private Date createTime;
    private Integer delay;
}