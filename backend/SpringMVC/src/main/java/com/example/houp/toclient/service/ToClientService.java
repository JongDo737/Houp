package com.example.houp.toclient.service;

import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toai.service.ToAIService;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.ReportToClient;
import com.example.houp.toclient.dto.UserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToClientService {
    private final ToAIService toAIService;

    public PredictionResponse getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return toAIService.getPredictedDiseaseInfo(userInfoRequest);
    }

    public ReportToClient getPredictedDisagnosisReport(CaseExamples caseExamples) {
        JudgementDocumentResponse judgementDocumentResponse = toAIService.getJudgementDocument(caseExamples);
        return convertToReportToClient(judgementDocumentResponse, caseExamples);
    }

    private ReportToClient convertToReportToClient(JudgementDocumentResponse response, CaseExamples caseExamples) {
        return new ReportToClient(
                response.approvalProbability(),
                response.result(),
                new ReportToClient.CaseExamples(
                        convertCase(response.caseExamples().firstCase(), caseExamples),
                        convertCase(response.caseExamples().secondCase(), caseExamples)
                )
        );
    }

    private ReportToClient.Case convertCase(JudgementDocumentResponse.Case similarCase, CaseExamples caseExamples) {
        return new ReportToClient.Case(
                caseExamples.caseExamples().get(similarCase.id()).reviewResult(),
                similarCase.summary(),
                caseExamples.caseExamples().get(similarCase.id()).judgmentDocument()
        );
    }
}
