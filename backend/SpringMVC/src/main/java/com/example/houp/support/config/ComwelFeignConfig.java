package com.example.houp.support.config;

import com.example.houp.support.exception.XmlParseToComwelException;
import com.example.houp.tocomwel.dto.ReportToObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import feign.Logger;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ComwelFeignConfig {

    @Bean
    Logger.Level comwelFeignLoggerLevel() {
        return Logger.Level.FULL; // 추후에 로깅 레벨 조절 필요. 현재는 개발 단계라서 FULL로 설정
    }

    @Bean
    public Decoder comwelFeignDecoder() {
        return new ResponseEntityDecoder((response, type) -> {
            try {
                InputStream bodyIs = response.body().asInputStream();
                XmlMapper xmlMapper = new XmlMapper();
                return xmlMapper.readValue(bodyIs, ReportToObject.class);
            } catch (IOException e) {
                throw new XmlParseToComwelException();
            }
        });
    }
}

