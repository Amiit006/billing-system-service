package com.rr.billingservice.service;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.feign.ClientServiceProxy;
import com.rr.billingservice.model.Payment;
import com.rr.billingservice.model.dto.ClientDto;
import com.rr.billingservice.model.dto.PaymentDto;
import com.rr.billingservice.model.dto.PaymentForm;
import com.rr.billingservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private ClientServiceProxy clientServiceProxy;

    @Autowired
    ClientOutstandingService clientOutstandingService;

    @Override
    public int generatePaymentId() {
        Optional<Payment> payment = paymentRepository.findTopByOrderByPaymentIdDesc();
        if(payment.isPresent()) return payment.get().getPaymentId() + 1;
        return 1;
    }

    @Override
    public Payment savePayment(PaymentForm paymentForm) throws InvoiceException {
        PaymentDto paymentDto = paymentForm.getPayment();
        ClientDto clientDto = paymentForm.getClient();
        LocalDateTime localDateTime = LocalDateTime.now();
        boolean result = clientServiceProxy.isClientPresent(clientDto);
        if (!result) {
            throw new InvoiceException("Client not found!", HttpStatus.NOT_FOUND);
        }

        Payment payment = Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .amount(paymentDto.getPaymentAmount())
                .paymentDate(paymentDto.getPaymentDate().toLocalDate())
                .paymentMode(paymentDto.getPaymentMode())
                .clientId(clientDto.getClientId())
                .createdDate(localDateTime).modifiedDate(localDateTime)
                .build();
        payment = paymentRepository.save(payment);
        clientOutstandingService.updateCustomerOutstanding(clientDto.getClientId());
        return payment;
    }

    @Override
    public List<Payment> getPaymentByClientId(int clientId) throws InvoiceException {
        clientServiceProxy.isClientPresentByClientId(clientId);
        return paymentRepository.findByClientId(clientId);
    }
}
