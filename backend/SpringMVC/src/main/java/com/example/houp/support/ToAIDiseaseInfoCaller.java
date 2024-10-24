package com.example.houp.support;

import com.example.houp.support.config.DiseaseFeignConfig;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${feign.client.toAI.disease.name}", url = "${feign.client.toAI.base-url}", configuration = DiseaseFeignConfig.class)
public interface ToAIDiseaseInfoCaller {

    @PostMapping("${feign.client.toAI.disease.disease-url}")
    PredictionResponse getPredictedDiseaseInfo(@RequestBody UserInfoRequest userInfoRequest);
}
