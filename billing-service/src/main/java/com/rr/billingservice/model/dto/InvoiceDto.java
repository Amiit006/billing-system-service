package com.rr.billingservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InvoiceDto {
    private int slNo;
    private String perticulars;
    private float amount;
    private int quanity;
    private float total;
    private String quantityType;
    private boolean verified;
}
