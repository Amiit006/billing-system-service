package com.rr.billingservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BillAmountDetailsDto {
    private float grandTotalAmount;
    private float subTotalAmount;
    private float taxAmount;
    private float taxPercentage;
}
