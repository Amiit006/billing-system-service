package com.rr.purchaseservice.controller;

import com.rr.purchaseservice.exception.PurchaseException;
import com.rr.purchaseservice.model.Payment;
import com.rr.purchaseservice.model.Purchase;
import com.rr.purchaseservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestParam("seasonId") int seasonId,
                                           @RequestParam("purchaseId") int purchaseId,
                                           @RequestBody Payment payment) {
        Payment payment1 = null;
        try {
            payment1 = paymentService.createPayment(seasonId, purchaseId, payment);
        } catch (PurchaseException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(payment1, HttpStatus.CREATED);

    }
}
