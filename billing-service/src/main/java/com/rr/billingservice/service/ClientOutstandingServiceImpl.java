package com.rr.billingservice.service;

import com.netflix.discovery.converters.Auto;
import com.rr.billingservice.exception.InvoiceException;
import com.rr.billingservice.model.ClientOutstanding;
import com.rr.billingservice.model.ClientOutstandingHistory;
import com.rr.billingservice.model.InvoiceOverView;
import com.rr.billingservice.model.Payment;
import com.rr.billingservice.repository.ClientOutstandingHistoryRepository;
import com.rr.billingservice.repository.ClientOutstandingRepository;
import com.rr.billingservice.repository.InvoiceOverviewRepository;
import com.rr.billingservice.repository.PaymentRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@Service
public class ClientOutstandingServiceImpl implements ClientOutstandingService {

    @Autowired
    InvoiceOverviewRepository invoiceOverviewRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ClientOutstandingRepository clientOutstandingRepository;

    @Autowired
    ClientOutstandingHistoryRepository clientOutstandingHistoryRepository;

    @Override
    public ClientOutstanding updateCustomerOutstanding(int clientId) throws InvoiceException {
        List<InvoiceOverView> clientInvoices = invoiceOverviewRepository.findByClientId(clientId);
        List<Payment> clientPayments = paymentRepository.findByClientId(clientId);
        Optional<ClientOutstanding> dbClientOutstanding = clientOutstandingRepository.findById(clientId);
        float invoiceTotal, paymentTotal;
        invoiceTotal = clientInvoices
                .stream()
                .map(InvoiceOverView::getGrandTotalAmount)
                .reduce((a,b) -> a+b)
                .orElseThrow(() -> new InvoiceException("Error while summing total purchase", HttpStatus.INTERNAL_SERVER_ERROR));

        paymentTotal = clientPayments
                .stream()
                .map(Payment::getAmount)
                .reduce((a,b) -> a+b)
                .orElseThrow(() -> new InvoiceException("Error while summing total payment", HttpStatus.INTERNAL_SERVER_ERROR));

        ClientOutstanding clientOutstanding;
        if(dbClientOutstanding.isPresent()) {
            clientOutstanding = dbClientOutstanding.get();
            clientOutstanding.setPurchasedAmount(invoiceTotal);
            clientOutstanding.setPaymentAmount(paymentTotal);
            clientOutstanding.setModifiedDate(LocalDateTime.now());
        } else {
            clientOutstanding = ClientOutstanding
                    .builder()
                    .clientId(clientId)
                    .purchasedAmount(invoiceTotal).paymentAmount(paymentTotal)
                    .modifiedDate(LocalDateTime.now())
                    .build();
        }
        clientOutstandingRepository.save(clientOutstanding);
        updateCustomerOutstandingHistory(clientOutstanding);
        return clientOutstanding;
    }

    private boolean updateCustomerOutstandingHistory(ClientOutstanding clientOutstanding) {
        ClientOutstandingHistory history = ClientOutstandingHistory
                .builder()
                .clientId(clientOutstanding.getClientId())
                .purchasedAmount(clientOutstanding.getPurchasedAmount())
                .paymentAmount(clientOutstanding.getPaymentAmount())
                .createdDate(LocalDateTime.now())
                .build();
        clientOutstandingHistoryRepository.save(history);
        return true;
    }
}
