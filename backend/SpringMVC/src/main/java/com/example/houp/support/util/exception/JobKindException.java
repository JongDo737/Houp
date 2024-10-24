package com.example.houp.support.util.exception;

public class JobKindException extends RuntimeException {

    private static final String MESSAGE = "직종유형이 올바르지 못합니다.";

    public JobKindException() {
        super(MESSAGE);
    }
}
