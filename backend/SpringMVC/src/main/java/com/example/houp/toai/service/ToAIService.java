package com.example.houp.toai.service;

import com.example.houp.support.ToAIDiseaseInfoCaller;
import com.example.houp.support.ToAIReportCaller;
import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.support.PredictionResponseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToAIService {
    private final ToAIDiseaseInfoCaller toAIDiseaseInfoCaller;
    private final ToAIReportCaller toAIReportCaller;

    public PredictionResponse getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        PredictionResponse predictionResponse = toAIDiseaseInfoCaller.getPredictedDiseaseInfo(userInfoRequest);
        PredictionResponseValidator.validate(predictionResponse);

        return predictionResponse;
    }

    public JudgementDocumentResponse getJudgementDocument(CaseExamples caseExamples) {
        return toAIReportCaller.getPredictedDisagnosisReport(caseExamples);
    }
}
