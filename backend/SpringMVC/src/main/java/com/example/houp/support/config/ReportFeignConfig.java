package com.example.houp.support.config;

import com.example.houp.support.exception.JsonParseToAiException;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;

public class ReportFeignConfig {

    @Bean
    Logger.Level reportFeignLoggerLevel() {
        return Logger.Level.FULL; // 추후에 로깅 레벨 조절 필요. 현재는 개발 단계라서 FULL로 설정
    }

    @Bean
    public Decoder reportFeignDecoder() {
        return new ResponseEntityDecoder((response, type) -> {
            try {
                InputStream bodyIs = response.body().asInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(bodyIs, JudgementDocumentResponse.class);
            } catch (IOException e) {
                throw new JsonParseToAiException();
            }
        });
    }
}
