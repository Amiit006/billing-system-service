package com.rr.billingservice.service;

import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.model.ClientOutstanding;
import com.rr.billingservice.model.ClientOutstandingHistory;

public interface ClientOutstandingService {

    ClientOutstanding updateCustomerOutstanding(int clientId) throws InvoiceException;
}
