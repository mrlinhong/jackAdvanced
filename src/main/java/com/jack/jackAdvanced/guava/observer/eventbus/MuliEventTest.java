package com.jack.jackAdvanced.guava.observer.eventbus;

import cn.hutool.core.map.MapUtil;
import com.google.common.eventbus.EventBus;

public class MuliEventTest {

    public static void main(String[] args){
        EventBus eventBus = new EventBus("testMultiple");
        //注册监听
        eventBus.register(new MultipleEventListener());
        //发送不同类型的事件
        eventBus.post(new EventA("EventA Message"));
        // 可以看到EventA可以正常发送到订阅者，EventB没有订阅者，会把消息发给DeadEvent
        eventBus.post(new EventB("EventB Message"));

    }
}
