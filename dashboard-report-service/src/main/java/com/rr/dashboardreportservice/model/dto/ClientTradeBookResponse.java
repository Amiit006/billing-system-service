package com.rr.dashboardreportservice.model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientTradeBookResponse {
    String clientName;
    String amount;
    LocalDate date;
    String type;
    String remark;
    double balance;

}
