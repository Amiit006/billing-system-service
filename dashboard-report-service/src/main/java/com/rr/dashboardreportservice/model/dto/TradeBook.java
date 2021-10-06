package com.rr.dashboardreportservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public interface TradeBook {

    int getId();

    int getClientId();

    double getAmount();

    LocalDate getBillPaymentDate();

    String getTransactionType();
}
