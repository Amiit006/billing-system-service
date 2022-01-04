package com.rr.purchaseservice.service;

import com.rr.purchaseservice.exception.PurchaseException;
import com.rr.purchaseservice.model.Payment;

public interface PaymentService {
    Payment createPayment(int seasonId, int purchaseId, Payment payment) throws PurchaseException;
}
