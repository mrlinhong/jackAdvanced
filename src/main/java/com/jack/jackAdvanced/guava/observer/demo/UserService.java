package com.jack.jackAdvanced.guava.observer.demo;

import com.google.common.eventbus.EventBus;

/**
 * 定义一个具体的业务实现类UserService
 */
public class UserService {

    public static void main(String[] args) {

        // 注册逻辑
        System.out.println("[register][执行用户({}) 的注册逻辑] ");

        // 发布消息
        EventBus eventBus = new EventBus("registerUserEvent");
        // 注册监听到eventBus
        eventBus.register(new EmailListener());
        eventBus.register(new PhoneListener());

        // 通过eventBus发送消息
        eventBus.post(new UserRegisterEvent("13711091587"));


    }


}
