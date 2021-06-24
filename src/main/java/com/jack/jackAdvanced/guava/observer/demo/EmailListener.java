package com.jack.jackAdvanced.guava.observer.demo;

import com.google.common.eventbus.Subscribe;

public class EmailListener {

    @Subscribe
    public void listener(UserRegisterEvent event){
        System.out.println("邮箱接收到新注册用户，给她发送邮件:" + event.getPhone());
    }
}
