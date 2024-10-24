package com.example.houp.toai.dto;

import java.util.List;

public record CaseExamples(
        String diseaseName,
        String jobKind,
        String diseaseKind,
        String painDescription,
        List<CaseExample> caseExamples
) {

    public record CaseExample(int id, String reviewResult, String judgmentDocument) {
    }
}