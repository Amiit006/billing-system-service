package com.rr.billingservice.controller;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.model.Payment;
import com.rr.billingservice.model.dto.PaymentForm;
import com.rr.billingservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/generatePaymentId")
    public ResponseEntity<?> generatePaymentId() {
        try {
            return new ResponseEntity(paymentService.generatePaymentId(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> savePayment(@RequestBody PaymentForm paymentForm) {
        try {
            Payment newPayment = paymentService.savePayment(paymentForm);
            return new ResponseEntity(Collections.singletonMap("response", "Payment saved successfully"), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
