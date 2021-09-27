package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.model.dto.ChartResponse;
import com.rr.dashboardreportservice.model.dto.TopBuyer;
import com.rr.dashboardreportservice.model.dto.TopSellingProducts;
import com.rr.dashboardreportservice.repository.TopContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopContentServiceImpl implements  TopContentService{

    @Autowired
    TopContentRepository topContentRepository;

    public List<TopSellingProducts> getTopSellingProducts(int topCount) {
        List<TopSellingProducts> topSellingProductList = topContentRepository.getTopSellingProducts(topCount);
        return topSellingProductList;
    }

    @Override
    public List<TopBuyer> getTopBuyer(int topCount) {
        List<TopBuyer> topBuyer = topContentRepository.getTopBuyer(topCount);
        return topBuyer;
    }

    @Override
    public List<ChartResponse> getSellCollectionStats(int year) {
        List<ChartResponse> chartResponseList = topContentRepository.getSellCollectionStats(year);
        return chartResponseList;
    }

    @Override
    public List<ChartResponse> getMonthlySellStats() {
        List<ChartResponse> chartResponseList = topContentRepository.getMonthlySellStats();
        return chartResponseList;
    }

}
