package com.example.mainserver.exceptions;




public class WrongEventCreationException extends RuntimeException {
    public WrongEventCreationException(String message) {
        super(message);
    }
}
