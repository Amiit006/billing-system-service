package com.rr.dashboardreportservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientReportResponse {
    List<CollectionStatsResponse> clientCollection;
    List<SellStatsResponse> clientSell;
}
