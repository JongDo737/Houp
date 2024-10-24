package com.example.houp.support;

import com.example.houp.support.config.ReportFeignConfig;
import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${feign.client.toAI.report.name}", url = "${feign.client.toAI.base-url}", configuration = ReportFeignConfig.class)
public interface ToAIReportCaller {

    @PostMapping("${feign.client.toAI.report.report-url}")
    JudgementDocumentResponse getPredictedDisagnosisReport(@RequestBody CaseExamples caseExamples);
}
