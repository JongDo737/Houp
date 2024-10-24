package com.example.houp.support;

import com.example.houp.support.config.ComwelFeignConfig;
import com.example.houp.tocomwel.dto.ReportToObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.client.toComwel.name}", url = "${feign.client.toComwel.base-url}", configuration = ComwelFeignConfig.class)
public interface ToComwelCaller {
    @GetMapping(value = "${feign.client.toComwel.get-report-url}", consumes = "application/xml")
    ReportToObject getDiseaseReportByDiseaseAndJobAndType(@RequestParam("serviceKey") String serviceKey,
                                                          @RequestParam("kindb") String jobKind,
                                                          @RequestParam("kindc") String diseaseKind,
                                                          @RequestParam("cq") String content,
                                                          @RequestParam("pageNo") String pageNo,
                                                          @RequestParam("numOfRows") String numOfRows);

    @GetMapping(value = "${feign.client.toComwel.get-report-url}", consumes = "application/xml")
    ReportToObject getDiseaseReportByJobAndType(@RequestParam("serviceKey") String serviceKey,
                                                @RequestParam("kindb") String jobKind,
                                                @RequestParam("kindc") String diseaseKind,
                                                @RequestParam("pageNo") String pageNo,
                                                @RequestParam("numOfRows") String numOfRows);

    @GetMapping(value = "${feign.client.toComwel.get-report-url}", consumes = "application/xml")
    ReportToObject getDiseaseReportByType(@RequestParam("serviceKey") String serviceKey,
                                          @RequestParam("kindc") String diseaseKind,
                                          @RequestParam("pageNo") String pageNo,
                                          @RequestParam("numOfRows") String numOfRows);

    @GetMapping(value = "${feign.client.toComwel.get-report-url}", consumes = "application/xml")
    ReportToObject getDiseaseReportByJob(@RequestParam("serviceKey") String serviceKey,
                                         @RequestParam("kindb") String jobKind,
                                         @RequestParam("pageNo") String pageNo,
                                         @RequestParam("numOfRows") String numOfRows);
}
