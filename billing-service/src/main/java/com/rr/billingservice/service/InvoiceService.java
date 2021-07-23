package com.rr.billingservice.service;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.model.InvoiceOverView;
import com.rr.billingservice.model.dto.BillAmountDetailsDto;
import com.rr.billingservice.model.dto.InvoiceDetailsDto;

import java.util.List;

public interface InvoiceService {

    boolean createBill(InvoiceDetailsDto invoiceDetailsDto) throws InvoiceException;

    boolean updateBill(int invoiceId, InvoiceDetailsDto invoiceDetailsDto) throws InvoiceException;

    int generateInvoiceId();

    InvoiceOverView getInvoiceById(int id) throws InvoiceException;

    List<InvoiceOverView> getInvoiceByClientId(int clientId);

    InvoiceOverView addDiscountToBill(int invoiceId, int clientId, BillAmountDetailsDto billAmountDetailsDto) throws InvoiceException;

}
