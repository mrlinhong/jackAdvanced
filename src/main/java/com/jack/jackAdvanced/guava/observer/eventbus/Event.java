package com.jack.jackAdvanced.guava.observer.eventbus;

/**
 * 定义事件对象
 */
public class Event {

    private String message;
    public Event(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
