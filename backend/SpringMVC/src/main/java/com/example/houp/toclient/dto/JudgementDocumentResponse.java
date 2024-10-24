package com.example.houp.toclient.dto;

public record JudgementDocumentResponse(
        double approvalProbability,
        String result,
        CaseExamples caseExamples
) {
    public record CaseExamples(
            Case firstCase,
            Case secondCase
    ) {}

    public record Case(
            int id,
            String summary
    ) {}
}
