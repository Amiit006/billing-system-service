package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.model.dto.ChartResponse;
import com.rr.dashboardreportservice.model.dto.TopBuyer;
import com.rr.dashboardreportservice.model.dto.TopSellingProducts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopContentService {

    List<TopSellingProducts> getTopSellingProducts(int topCount);

    List<TopBuyer> getTopBuyer(int topCount);

    List<ChartResponse> getSellCollectionStats(int year);

    List<ChartResponse> getMonthlySellStats();
}
