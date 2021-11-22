package com.rr.dashboardreportservice.model.dto;

public interface ClientOutstandingAmount {
    int getSlNo();
    String getClientName();
    double getPurchasedAmount();
    double getPaymentAmount();
    double getOutstandingAmount();
}
