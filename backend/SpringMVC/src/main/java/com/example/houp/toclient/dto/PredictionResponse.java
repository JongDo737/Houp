package com.example.houp.toclient.dto;

import java.util.Map;

public record PredictionResponse(
        DiseaseDetails diseaseDetails,
        Rankings rankings,
        Hospitalization hospitalization,
        Outpatient outpatient,
        String jobKind,
        String diseaseKind
) {
    public record DiseaseDetails(
            String name,
            String category,
            String description,
            int rank,
            int patients,
            int medicalCost
    ) {}

    public record Rankings(
            Place place1,
            Place place2,
            Place place3
    ) {
        public record Place(
                String name,
                int patients
        ) {}
    }

    public static record Hospitalization(
            double rate,
            Map<String, Integer> trend,
            double averageDays
    ) {}

    public static record Outpatient(
            double rate,
            Map<String, Integer> trend,
            double averageDays
    ) {}
}
