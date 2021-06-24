package com.jack.jackAdvanced.guava.observer.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class MultipleEventListener {

    @Subscribe
    public void listenerEventA(EventA eventA){
        System.out.println("subscribe EventA:" + eventA.getMessage());
    }

//    @Subscribe
//    public void listenerEventB(EventB eventB){
//        System.out.println("subscribe EventB:" + eventB.getMessage());
//    }

    // DeadEvent就是用来接收这种没有订阅者的消息
    @Subscribe
    public void listenerDeadEvent(DeadEvent deadEvent){
        System.out.println("deadEvent:" + deadEvent.getEvent());
    }
}
