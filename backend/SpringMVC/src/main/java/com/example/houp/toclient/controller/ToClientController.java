package com.example.houp.toclient.controller;

import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.ReportToClient;
import com.example.houp.toclient.dto.UserDiseaseInfoRequest;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.service.ToClientService;
import com.example.houp.tocomwel.service.ToComwelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ToClientController {
    private final ToClientService toClientService;
    private final ToComwelService toComwelService;

    @PostMapping("/disease-predictions")
    public ResponseEntity<PredictionResponse> handleUserInfo(@Valid @RequestBody UserInfoRequest userInfoRequest) {
        return ResponseEntity.ok(toClientService.getPredictedDiseaseInfo(userInfoRequest));
    }

    @PostMapping("/worker-compensation-infos")
    public ResponseEntity<ReportToClient> handleDiseaseInfo(@Valid @RequestBody UserDiseaseInfoRequest userDiseaseInfoRequest) {
        CaseExamples relatedDiseaseJudgments = toComwelService.getDiseaseDisagnosisReport(userDiseaseInfoRequest);
        return ResponseEntity.ok(toClientService.getPredictedDisagnosisReport(relatedDiseaseJudgments));
    }
}
