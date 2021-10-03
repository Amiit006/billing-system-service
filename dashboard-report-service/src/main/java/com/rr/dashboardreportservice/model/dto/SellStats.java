package com.rr.dashboardreportservice.model.dto;

import java.time.LocalDate;

public interface SellStats {
    int getInvoiceId();
    int getClientId();
    LocalDate getInvoiceDate();
    double getSubTotalAmount();
    double getTaxAmount();
    double getDiscountAmount();
    double getGrandTotalAmount();
}
