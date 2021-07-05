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
    private int invoiceId;
    private int slNo;
    private String perticulars;
    private double amount;
    private double discount;
    private double discountPrice;
    private int quanity;
    private double total;
    private String quantityType;
    private boolean verified;
}
