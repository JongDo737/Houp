package com.example.houp.support.config;

import com.example.houp.support.exception.JsonParseToAiException;
import com.example.houp.toclient.dto.PredictionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class DiseaseFeignConfig {

    @Bean
    Logger.Level diseaseFeignLoggerLevel() {
        return Logger.Level.FULL; // 추후에 로깅 레벨 조절 필요. 현재는 개발 단계라서 FULL로 설정
    }

    @Bean
    public Decoder diseaseFeignDecoder() {
        return new ResponseEntityDecoder((response, type) -> {
            try {
                InputStream bodyIs = response.body().asInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(bodyIs, PredictionResponse.class);
            } catch (IOException e) {
                throw new JsonParseToAiException();
            }
        });
    }
}
