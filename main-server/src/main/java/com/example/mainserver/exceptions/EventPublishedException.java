package com.example.mainserver.exceptions;




public class EventPublishedException extends RuntimeException {
    public EventPublishedException(String message) {
        super(message);
    }
}
