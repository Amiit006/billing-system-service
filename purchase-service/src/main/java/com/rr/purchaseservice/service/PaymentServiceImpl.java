package com.rr.purchaseservice.service;

import com.rr.purchaseservice.exception.PurchaseException;
import com.rr.purchaseservice.model.Payment;
import com.rr.purchaseservice.model.Purchase;
import com.rr.purchaseservice.repository.PaymentRepository;
import com.rr.purchaseservice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Payment createPayment(int seasonId, int purchaseId, Payment payment) throws PurchaseException {
        Purchase purchase = purchaseRepository.findBySeasonIdAndPurchaseId(seasonId, purchaseId)
                .orElseThrow(() -> new PurchaseException("Purchase not found!", HttpStatus.NOT_FOUND));
        Stream<Float> purchaseStream = purchase.getPayments().stream().map(Payment::getAmount);
        float totalPaymentBefore = purchaseStream.reduce((a,b) -> a+b)
                .orElse((float) 0.0);
        if(totalPaymentBefore + payment.getAmount() > purchase.getPurchaseAmount()) {
            throw new PurchaseException("unexpected amount", HttpStatus.BAD_REQUEST);
        }
        LocalDateTime now = LocalDateTime.now();
        payment.setPurchase(purchase);
        payment.setCreatedDate(now);
        payment.setModifiedDate(now);
        Payment payment1 = paymentRepository.save(payment);
        return payment1;
    }
}
