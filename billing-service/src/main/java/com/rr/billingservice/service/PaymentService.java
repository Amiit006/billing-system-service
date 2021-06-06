package com.rr.billingservice.service;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.model.Payment;
import com.rr.billingservice.model.dto.PaymentForm;

import java.util.List;

public interface PaymentService {

    int generatePaymentId();

    Payment savePayment(PaymentForm payment) throws InvoiceException;

    List<Payment> getPaymentByClientId(int clientId) throws InvoiceException;
}
