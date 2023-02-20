package com.example.mainserver.exceptions;




public class WrongNameException extends RuntimeException {
    public WrongNameException(String message) {
        super(message);
    }
}
