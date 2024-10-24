package com.example.houp.toclient.dto;

public record ReportToClient(
        double approvalProbability,
        String result,
        CaseExamples caseExamples
) {
    public record CaseExamples(
            Case firstCase,
            Case secondCase
    ) {}

    public record Case(
            String reviewResult,
            String summary,
            String judgmentDocument
    ) {}
}
