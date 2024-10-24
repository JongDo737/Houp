package com.example.houp.tocomwel.support;

import com.example.houp.support.ToComwelCaller;
import com.example.houp.tocomwel.dto.ReportToObject;

public class JobTypeStrategy implements ReportStrategy {
    private final String jobKind;
    private final String diseaseKind;

    private JobTypeStrategy(String jobKind, String diseaseKind) {
        this.jobKind = jobKind;
        this.diseaseKind = diseaseKind;
    }

    public static JobTypeStrategy of(String jobKind, String diseaseKind) {
        return new JobTypeStrategy(jobKind, diseaseKind);
    }

    @Override
    public ReportToObject getReports(ToComwelCaller caller, String serviceKey, String pageNo, String numOfRows) {
        return caller.getDiseaseReportByJobAndType(serviceKey, jobKind, diseaseKind, pageNo, numOfRows);
    }
}
