package com.jack.jackAdvanced.guava.observer.eventbus;

public class EventA {

    private String message;
    public EventA(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
