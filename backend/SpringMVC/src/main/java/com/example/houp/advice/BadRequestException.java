package com.example.houp.advice;

public class BadRequestException extends BusinessException {

    public BadRequestException(String message) {
        super(message);
    }
}
