package com.jack.jackAdvanced.guava.observer.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 定义事件监听
 */
public class EventListener {

    @Subscribe
    public void listener(Event event){
        System.out.println("Subscribe 接收消息:" + event.getMessage());
    }
}
