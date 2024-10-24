package com.example.houp.toclient.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDiseaseInfoRequest(
        @NotBlank(message = "질병명은 비어있을 수 없습니다")
        String diseaseName,
        @NotBlank(message = "직종유형은 비어있을 수 없습니다")
        String jobKind,
        @NotBlank(message = "질병유형은 비어있을 수 없습니다")
        String diseaseKind,
        @NotBlank(message = "통증 설명은 비어있을 수 없습니다")
        String painDescription
) {
}
