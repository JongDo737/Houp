package com.example.houp.support.util.exception;

public class UrlDecodingException extends RuntimeException {

    private static final String MESSAGE = "잘못 인코딩된 URL입니다.";

    public UrlDecodingException() {
        super(MESSAGE);
    }
}
