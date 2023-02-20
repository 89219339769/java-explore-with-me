package com.example.mainserver.exceptions;


public class  WrongTimeEventCreationException extends RuntimeException {
    public  WrongTimeEventCreationException(String message) {
        super(message);
    }
}