package com.rr.dashboardreportservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public interface TopSellingProducts {
    String getPerticulars();
    double getTotalSell();
}
