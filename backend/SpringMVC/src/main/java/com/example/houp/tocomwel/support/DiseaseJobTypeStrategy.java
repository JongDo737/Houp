package com.example.houp.tocomwel.support;

import com.example.houp.support.ToComwelCaller;
import com.example.houp.tocomwel.dto.ReportToObject;

public class DiseaseJobTypeStrategy implements ReportStrategy {
    private final String diseaseName;
    private final String jobKind;
    private final String diseaseKind;

    private DiseaseJobTypeStrategy(String diseaseName, String jobKind, String diseaseKind) {
        this.diseaseName = diseaseName;
        this.jobKind = jobKind;
        this.diseaseKind = diseaseKind;
    }

    public static DiseaseJobTypeStrategy of(String diseaseName, String jobKind, String diseaseKind) {
        return new DiseaseJobTypeStrategy(diseaseName, jobKind, diseaseKind);
    }

    @Override
    public ReportToObject getReports(ToComwelCaller caller, String serviceKey, String pageNo, String numOfRows) {
        return caller.getDiseaseReportByDiseaseAndJobAndType(serviceKey, jobKind, diseaseKind, diseaseName, pageNo, numOfRows);
    }
}
