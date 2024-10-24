package com.example.houp.support.exception;

public class JsonParseToAiException extends RuntimeException {

    private static final String MESSAGE = "AI로부터 오는 데이터에 문제가 발생했습니다.";

    public JsonParseToAiException() {
        super(MESSAGE);
    }

}
