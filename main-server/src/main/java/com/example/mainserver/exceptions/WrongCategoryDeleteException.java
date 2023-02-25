package com.example.mainserver.exceptions;



public class WrongCategoryDeleteException extends RuntimeException {
    public WrongCategoryDeleteException(String message) {
        super(message);
    }
}