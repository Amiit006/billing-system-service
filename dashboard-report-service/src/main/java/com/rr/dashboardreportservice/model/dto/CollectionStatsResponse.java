package com.rr.dashboardreportservice.model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CollectionStatsResponse {
    int paymentId;
    int clientId;
    String clientName;
    String mobile;
    double amount;
    String paymentMode;
    LocalDate paymentDate;
}
