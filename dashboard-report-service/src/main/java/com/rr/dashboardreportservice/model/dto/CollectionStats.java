package com.rr.dashboardreportservice.model.dto;

import java.time.LocalDate;

public interface CollectionStats {
    int getPaymentId();
    int getClientId();
    double getAmount();
    String getPaymentMode();
    LocalDate getPaymentDate();
}
