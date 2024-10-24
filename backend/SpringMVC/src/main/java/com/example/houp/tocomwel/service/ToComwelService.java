package com.example.houp.tocomwel.service;

import com.example.houp.support.ToComwelCaller;
import com.example.houp.support.util.KindValidator;
import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.UserDiseaseInfoRequest;
import com.example.houp.tocomwel.dto.ReportToObject;
import com.example.houp.tocomwel.dto.StrategyResult;
import com.example.houp.tocomwel.support.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class ToComwelService {

    private static final String PAGENO = "1";

    private static final int MAX_REPORTS = 20;

    @Value("${feign.client.toComwel.service-key}")
    private String serviceKey;

    private final ToComwelCaller toComwelCaller;

    public CaseExamples getDiseaseDisagnosisReport(UserDiseaseInfoRequest userDiseaseInfoRequest) {
//        Decoded decoded = Decoded.of(diseaseName, jobKind, diseaseKind);
        validate(userDiseaseInfoRequest.jobKind(), userDiseaseInfoRequest.diseaseKind());

        List<ReportToObject.Item> collectReports = getDiseaseReportsCascade(userDiseaseInfoRequest.diseaseName(), userDiseaseInfoRequest.jobKind(), userDiseaseInfoRequest.diseaseKind());
        return createCaseExamples(userDiseaseInfoRequest.diseaseName(), userDiseaseInfoRequest.jobKind(), userDiseaseInfoRequest.diseaseKind(), userDiseaseInfoRequest.painDescription(), collectReports);
    }

    private static void validate(String decodedJobKind, String decodedDiseaseKind) {
        KindValidator.isValidJobKind(decodedJobKind);
        KindValidator.isValidDiseaseKind(decodedDiseaseKind);
    }

    private List<ReportToObject.Item> getDiseaseReportsCascade(String diseaseName, String jobKind, String diseaseKind) {
        List<ReportStrategy> strategies = getReportStrategies(diseaseName, jobKind, diseaseKind);
        return collectReportsFromStrategies(strategies);
    }

    private List<ReportToObject.Item> collectReportsFromStrategies(List<ReportStrategy> strategies) {
        AtomicInteger remainingCount = new AtomicInteger(MAX_REPORTS);

        return strategies.stream()
                .takeWhile(strategy -> remainingCount.get() > 0)
                .flatMap(strategy -> {
                    StrategyResult result = executeStrategy(strategy, remainingCount.get());
                    remainingCount.set(result.remainingCount());
                    return Optional.ofNullable(result.items())
                            .stream()
                            .flatMap(Collection::stream);
                })
                .toList();
    }

    private List<ReportStrategy> getReportStrategies(String diseaseName, String jobKind, String diseaseKind) {
        return Arrays.asList(
                DiseaseJobTypeStrategy.of(diseaseName, jobKind, diseaseKind),
                JobTypeStrategy.of(jobKind, diseaseKind),
                TypeStrategy.of(diseaseKind),
                JobStrategy.of(jobKind)
        );
    }

    private StrategyResult executeStrategy(ReportStrategy strategy, int count) {
        ReportToObject reportToObject = getReportToObject(strategy, count);
        int totalCount = Integer.parseInt(reportToObject.getBody().getTotalCount());

        List<ReportToObject.Item> reports = reportToObject.getBody().getItems().getItem();

        return StrategyResult.of(reports, count - totalCount);
    }

    private ReportToObject getReportToObject(ReportStrategy strategy, int count) {
        return strategy.getReports(toComwelCaller, serviceKey, PAGENO, String.valueOf(count));
    }

    private CaseExamples createCaseExamples(String diseaseName, String jobKind, String diseaseKind, String painDescription, List<ReportToObject.Item> items) {
        AtomicInteger index = new AtomicInteger(0);

        return new CaseExamples(diseaseName, jobKind, diseaseKind, painDescription,
                items.stream()
                        .distinct()
                        .map(item -> new CaseExamples.CaseExample(index.getAndIncrement(), item.getKinda(), item.getNoncontent()))
                        .limit(MAX_REPORTS)
                        .toList());
    }
}
