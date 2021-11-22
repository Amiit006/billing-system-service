package com.rr.dashboardreportservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ClientOutStandingReport {
    public List<ClientOutstandingAmountSummary> clientOutstandingAmountSummary;
    public List<ClientOutstandingAmount> clientOutstandingAmount;
}
