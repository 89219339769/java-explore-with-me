package com.example.mainserver.exceptions;




public class WrongDatePatchException extends RuntimeException {
    public WrongDatePatchException(String message) {
        super(message);
    }
}
