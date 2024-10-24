package com.example.houp.support.exception;

public class XmlParseToComwelException extends RuntimeException{

    private static final String MESSAGE = "질병판정서 XML 파일을 파싱하는 과정에서 에러가 발생했습니다.";

    public XmlParseToComwelException() {
        super(MESSAGE);
    }
}
