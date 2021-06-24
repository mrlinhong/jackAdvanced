package com.jack.jackAdvanced.guava.observer.eventbus;

import com.google.common.eventbus.EventBus;

public class MessageEventDemo {

    public static void main(String[] args){
        EventBus eventBus = new EventBus("test");
        //注册监听到eventBus
        eventBus.register(new EventListener());
        //通过eventBus发送两笔消息
        eventBus.post(new Event("send message1"));
        eventBus.post(new Event("send message2"));
    }
}
