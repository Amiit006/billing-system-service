package com.rr.billingservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InvoiceDetailsDto {
    private List<InvoiceDto> invoice;
    private BillAmountDetailsDto billAmountDetails;
    private ClientDto client;
    private PaymentDto payment;
}
