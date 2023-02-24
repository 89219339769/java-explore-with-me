package com.example.mainserver.exceptions;




public class WrongPatchException extends RuntimeException {
    public WrongPatchException(String message) {
        super(message);
    }
}
