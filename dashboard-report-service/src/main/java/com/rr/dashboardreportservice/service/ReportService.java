package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.model.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ReportService {

    List<SellStatsResponse> getSellsReport(LocalDate form_date, LocalDate to_date);

    List<CollectionStatsResponse> getCollectionsReport(LocalDate form_date, LocalDate to_date);

    ClientReportResponse getClientReport(LocalDate form_date, LocalDate to_date, int clientId);

}
