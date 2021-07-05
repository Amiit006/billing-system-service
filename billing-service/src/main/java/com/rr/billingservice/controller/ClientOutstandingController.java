package com.rr.billingservice.controller;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.service.ClientOutstandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/client-outstanding")
public class ClientOutstandingController {

    @Autowired
    ClientOutstandingService clientOutstandingService;

    @GetMapping
    public ResponseEntity<?> getOutstandingById(@RequestParam("clientId") int id) {
        try {
            double outstanding = clientOutstandingService.getClientOutStandingByClientId(id);
            return new ResponseEntity(outstanding, HttpStatus.OK);
        } catch (InvoiceException ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error Occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
