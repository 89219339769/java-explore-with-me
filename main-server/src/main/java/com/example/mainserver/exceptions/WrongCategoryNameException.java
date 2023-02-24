package com.example.mainserver.exceptions;




public class WrongCategoryNameException extends RuntimeException {
    public WrongCategoryNameException(String message) {
        super(message);
    }
}
