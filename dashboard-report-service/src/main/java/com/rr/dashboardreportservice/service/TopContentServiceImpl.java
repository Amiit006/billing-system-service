package com.rr.dashboardreportservice.service;

import com.rr.dashboardreportservice.model.dto.ChartResponse;
import com.rr.dashboardreportservice.model.dto.TopBuyer;
import com.rr.dashboardreportservice.model.dto.TopSellingProducts;
import com.rr.dashboardreportservice.repository.TopContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public List<ChartResponse> getSellStats(LocalDate from_date, LocalDate to_date) {
        List<ChartResponse> chartResponseList = topContentRepository.getSellStats(from_date, to_date);
        return chartResponseList;
    }

    @Override
    public List<ChartResponse> getCollectionStats(LocalDate from_date, LocalDate to_date) {
        List<ChartResponse> chartResponseList = topContentRepository.getCollectionStats(from_date, to_date);
        return chartResponseList;
    }

    @Override
    public List<ChartResponse> getSellCollectionStatsByClientId(LocalDate from_date, LocalDate to_date, int clientId) {
        List<ChartResponse> chartResponseList = topContentRepository.getSellCollectionStatsByClientId(from_date, to_date, clientId);
        return chartResponseList;
    }

    @Override
    public List<ChartResponse> getClientOutstanding(int clientId) {
        List<ChartResponse> chartResponseList = topContentRepository.getClientOutstanding(clientId);
        return chartResponseList;
    }

}
