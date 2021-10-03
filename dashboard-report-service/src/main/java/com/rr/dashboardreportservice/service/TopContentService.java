package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.model.dto.ChartResponse;
import com.rr.dashboardreportservice.model.dto.TopBuyer;
import com.rr.dashboardreportservice.model.dto.TopSellingProducts;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TopContentService {

    List<TopSellingProducts> getTopSellingProducts(int topCount);

    List<TopBuyer> getTopBuyer(int topCount);

    List<ChartResponse> getSellCollectionStats(int year);

    List<ChartResponse> getMonthlySellStats();

    List<ChartResponse> getSellStats(LocalDate from_date, LocalDate to_date);

    List<ChartResponse> getCollectionStats(LocalDate from_date, LocalDate to_date);
}
