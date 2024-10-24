package com.example.houp.support.util.exception;

public class DiseaseKindException extends RuntimeException {

    private static final String MESSAGE = "질병유형이 올바르지 못합니다.";

    public DiseaseKindException() {
        super(MESSAGE);
    }
}
