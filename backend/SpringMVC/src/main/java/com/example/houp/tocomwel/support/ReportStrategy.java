package com.example.houp.tocomwel.support;

import com.example.houp.support.ToComwelCaller;
import com.example.houp.tocomwel.dto.ReportToObject;

import java.util.List;

public interface ReportStrategy {

    ReportToObject getReports(ToComwelCaller caller, String serviceKey, String pageNo, String numOfRows);
}
