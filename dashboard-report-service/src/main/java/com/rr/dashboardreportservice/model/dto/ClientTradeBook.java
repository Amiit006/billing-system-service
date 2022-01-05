package com.rr.dashboardreportservice.model.dto;

import java.time.LocalDate;

public interface ClientTradeBook {
    int getClientId();
    int getId();
    double getAmount();
    LocalDate getDate();
    String getType();
    String getRemark();
}
