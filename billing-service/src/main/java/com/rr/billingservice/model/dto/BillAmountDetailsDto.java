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
    private double grandTotalAmount;
    private double subTotalAmount;
    private double taxAmount;
    private double taxPercentage;
    private double overallDiscountPercentage;
    private double overallDiscountAmount;
}
