package com.example.houp.tocomwel.dto;

import com.example.houp.support.util.KindValidator;
import com.example.houp.support.util.UrlUtility;

public record Decoded(
        String diseaseName,
        String jobKind,
        String diseaseKind
) {

    public static Decoded of(String diseaseName, String jobKind, String diseaseKind) {
        String decodedDiseaseName = UrlUtility.decode(diseaseName);
        String decodedJobKind = UrlUtility.decode(jobKind);
        String decodedDiseaseKind = UrlUtility.decode(diseaseKind);

        validate(decodedJobKind, decodedDiseaseKind);

        return new Decoded(decodedDiseaseName, decodedJobKind, decodedDiseaseKind);
    }

    private static void validate(String decodedJobKind, String decodedDiseaseKind) {
        KindValidator.isValidJobKind(decodedJobKind);
        KindValidator.isValidDiseaseKind(decodedDiseaseKind);
    }
}
