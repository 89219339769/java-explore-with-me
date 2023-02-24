package com.example.mainserver.exceptions;




public class UserBadNameException extends RuntimeException {
    public UserBadNameException(String message) {
        super(message);
    }
}
