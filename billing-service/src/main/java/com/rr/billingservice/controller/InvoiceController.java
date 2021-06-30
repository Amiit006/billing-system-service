package com.rr.billingservice.controller;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.model.InvoiceOverView;
import com.rr.billingservice.model.dto.InvoiceDetailsDto;
import com.rr.billingservice.repository.InvoiceOverviewRepository;
import com.rr.billingservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceOverviewRepository overviewRepository;

    @GetMapping("/generateInvoiceId")
    public ResponseEntity<?> generateInvoiceId() {
        int invoiceId = invoiceService.generateInvoiceId();
        return new ResponseEntity(invoiceId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable("id") int id) {
        try {
            InvoiceOverView invoiceOverView = invoiceService.getInvoiceById(id);
            return new ResponseEntity(invoiceOverView, HttpStatus.OK);
        } catch (InvoiceException e) {
            return new ResponseEntity(Collections.singletonMap("error", e.getException()), e.getStatus());
        }
    }

    @GetMapping("/client")
    public ResponseEntity<?> getInvoiceByClientId(@RequestParam("clientId") int id) {
        List<InvoiceOverView> invoices = invoiceService.getInvoiceByClientId(id);
        return new ResponseEntity(invoices, HttpStatus.OK);
    }

    @PostMapping(value = "/createBill")
    public ResponseEntity<?> createBill(@RequestBody InvoiceDetailsDto invoiceDetailsDto) throws InvoiceException {
        try {
            invoiceService.createBill(invoiceDetailsDto);
            return new ResponseEntity(Collections.singletonMap("response", "Invoice saved successfully"), HttpStatus.CREATED);
        } catch (InvoiceException ex) {
            return new ResponseEntity<>(Collections.singletonMap("error", ex.getException()), ex.getStatus());
        }
    }

    @PutMapping(value = "/updateBill/{id}")
    public ResponseEntity<?> updateBill(@PathVariable("id") int invoiceId, @RequestBody InvoiceDetailsDto invoiceDetailsDto) throws InvoiceException {
        try {
            invoiceService.updateBill(invoiceId, invoiceDetailsDto);
            return new ResponseEntity(Collections.singletonMap("response", "Invoice saved successfully"), HttpStatus.CREATED);
        } catch (InvoiceException ex) {
            return new ResponseEntity<>(ex.getException(), ex.getStatus());
        }
    }
}
