package com.jack.jackAdvanced.guava.observer.demo;

/**
 *  用户注册事件观察者模式对我们很大的一个作用，在于实现业务的解耦。
 *  来个简单的场景，以用户注册的场景来举例子，假设在用户注册完成时，需要给该用户发送邮件、发送优惠劵等等操作，如下图所示
 *  定义一个事件（此例就是用户注册事件）
 */
public class UserRegisterEvent {

    private String phone;
    public UserRegisterEvent(String phone){
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
