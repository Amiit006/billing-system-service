package com.rr.purchaseservice.model.dto;

import java.time.LocalDate;

public class PurchaseDto {
    String partyName;
    LocalDate purchaseDate;
    double purchaseAmount;
    double taxPercent;
    double taxAmount;
    double discountPercent;
    double discountAmount;
    double extraDiscountAmount;
    TransportDto transport;
}
