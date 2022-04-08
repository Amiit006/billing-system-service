package com.rr.purchaseservice.service;

import com.rr.purchaseservice.model.ChartResponse;

import java.util.List;

public interface DashboardService {

    List<ChartResponse> getPurchasePaymentStats(int seasonId);
}
