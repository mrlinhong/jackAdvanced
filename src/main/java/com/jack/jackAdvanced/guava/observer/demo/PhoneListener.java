package com.jack.jackAdvanced.guava.observer.demo;

import com.google.common.eventbus.Subscribe;
import com.jack.jackAdvanced.guava.observer.eventbus.Event;

public class PhoneListener {

    @Subscribe
    public void listener(UserRegisterEvent event){
        System.out.println("短信平台接收到新注册用户，给她发送短信:" + event.getPhone());
    }
}
