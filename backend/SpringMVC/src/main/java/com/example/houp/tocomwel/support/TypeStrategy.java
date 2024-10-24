package com.example.houp.tocomwel.support;

import com.example.houp.support.ToComwelCaller;
import com.example.houp.tocomwel.dto.ReportToObject;

public class TypeStrategy implements ReportStrategy {
    private final String diseaseKind;

    private TypeStrategy(String diseaseKind) {
        this.diseaseKind = diseaseKind;
    }

    public static TypeStrategy of(String diseaseKind) {
        return new TypeStrategy(diseaseKind);
    }

    @Override
    public ReportToObject getReports(ToComwelCaller caller, String serviceKey, String pageNo, String numOfRows) {
        return caller.getDiseaseReportByType(serviceKey, diseaseKind, pageNo, numOfRows);
    }
}