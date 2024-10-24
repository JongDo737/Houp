package com.example.houp.tocomwel.dto;

import java.util.List;

public record StrategyResult(
        List<ReportToObject.Item> items,
        int remainingCount
) {

    public static StrategyResult of(List<ReportToObject.Item> items, int remainingCount) {
        return new StrategyResult(items, remainingCount);
    }
}
