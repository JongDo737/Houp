package com.example.houp.toclient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserInfoRequest(
        @NotBlank(message = "이름은 비어있을 수 없습니다")
        String name,

        @NotBlank(message = "성별은 비어있을 수 없습니다")
        @Pattern(regexp = "^(여성|남성)$", message = "성별은 '여성' 또는 '남성'이어야 합니다")
        String gender,

        @NotBlank(message = "연령대는 비어있을 수 없습니다")
        @Pattern(regexp = "^(10대 이하|10대|20대|30대|40대|50대|60대|70대|80대)$",
                message = "연령대는 '10대 이하', '10대', '20대', '30대', '40대', '50대', '60대', '70대', '80대' 중 하나여야 합니다")
        String ageGroup,

        @NotBlank(message = "직업은 비어있을 수 없습니다")
        String job,

        @NotBlank(message = "통증 부위는 비어있을 수 없습니다")
        @Pattern(regexp = "^(이마|왼쪽 어깨|오른쪽 어깨|가슴|몸통|왼쪽 팔꿈치|오른쪽 팔꿈치|왼쪽 손\\(손목 포함\\)|오른쪽 손\\(손목 포함\\)|골반 및 엉덩이|성기|왼쪽 허벅지|오른쪽 허벅지|왼쪽 무릎|오른쪽 무릎|왼쪽 정강이|오른쪽 정강이|왼쪽 발목|오른쪽 발목|왼쪽 발|오른쪽 발)$",
                message = "유효하지 않은 통증 부위입니다. 허용된 값 중 하나를 선택해주세요.")
        String painArea,

        @NotBlank(message = "통증 설명은 비어있을 수 없습니다")
        String painDescription
) {
}
