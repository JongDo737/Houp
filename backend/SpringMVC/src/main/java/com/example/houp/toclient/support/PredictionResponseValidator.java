package com.example.houp.toclient.support;

import com.example.houp.support.util.KindValidator;
import com.example.houp.toclient.dto.PredictionResponse;

public class PredictionResponseValidator {

    public static void validate(PredictionResponse predictionResponse) {
        KindValidator.isValidDiseaseKind(predictionResponse.diseaseKind());
        KindValidator.isValidJobKind(predictionResponse.jobKind());
    }
}
