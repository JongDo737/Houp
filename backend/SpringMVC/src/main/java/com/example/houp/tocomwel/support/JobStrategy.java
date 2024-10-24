package com.example.houp.tocomwel.support;

import com.example.houp.support.ToComwelCaller;
import com.example.houp.tocomwel.dto.ReportToObject;

public class JobStrategy implements ReportStrategy {
    private final String jobKind;

    private JobStrategy(String jobKind) {
        this.jobKind = jobKind;
    }

    public static JobStrategy of(String jobKind) {
        return new JobStrategy(jobKind);
    }

    @Override
    public ReportToObject getReports(ToComwelCaller caller, String serviceKey, String pageNo, String numOfRows) {
        return caller.getDiseaseReportByJob(serviceKey, jobKind, pageNo, numOfRows);
    }
}
