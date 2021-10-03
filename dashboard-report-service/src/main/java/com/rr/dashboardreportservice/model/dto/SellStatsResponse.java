package com.rr.dashboardreportservice.model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SellStatsResponse {
    int invoiceId;
    int clientId;
    String clientName;
    String mobile;
    LocalDate invoiceDate;
    double subTotalAmount;
    double taxAmount;
    double discountAmount;
    double grandTotalAmount;
}
