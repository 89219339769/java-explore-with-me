package com.example.mainserver.exceptions;


import com.example.mainserver.event.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

//
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse objectWrongEnterExeption(final ItemUnvailableException e) {
//        return new ErrorResponse(e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse objectWrongEnterExeption(final BadRequestException e) {
//        return new ErrorResponse(e.getMessage());
//    }
//
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ErrorResponse objectWrongEnterExeption(final EmailWrongException e) {
//        return new ErrorResponse(e.getMessage());
//    }



    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse objectWrongEnterExeption(final CategoryNotFoundException e) {
        return new ErrorResponse(e.getMessage(), "BAD_REQUEST", "Incorrectly made request.",
                LocalDateTime.now());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse objectWrongEnterExeption(final WrongDateException e) {
        return new ErrorResponse(e.getMessage(), "FORBIDDEN", "For the requested operation the conditions are not met.",
                LocalDateTime.now());
    }



}
