package com.rr.dashboardreportservice.model.dto;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TradeBookResponse {

    int id;
    int clientId;
    String clientName;
    double amount;
    LocalDate billPaymentDate;
    String transactionType;
}
