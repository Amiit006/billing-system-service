package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.model.dto.ClientReportResponse;
import com.rr.dashboardreportservice.model.dto.CollectionStatsResponse;
import com.rr.dashboardreportservice.model.dto.SellStatsResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ClientReportService {

    List<CollectionStatsResponse> getClientCollectionsReport(LocalDate form_date, LocalDate to_date, int clientId);
    List<SellStatsResponse> getClientSellsReport(LocalDate form_date, LocalDate to_date, int clientId);

}
