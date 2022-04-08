package com.rr.purchaseservice.model.dto;

import java.time.LocalDate;

public class PaymentDto {
    String mode;
    int chequeNo;
    LocalDate paymentDate;
    String remark;
    double amount;
}
