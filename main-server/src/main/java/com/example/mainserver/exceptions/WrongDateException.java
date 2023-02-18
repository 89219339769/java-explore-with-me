package com.example.mainserver.exceptions;


public class WrongDateException extends RuntimeException {
    public WrongDateException(String message) {
        super(message);
    }
}