package com.rr.billingservice.repository;

import com.rr.billingservice.model.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {

    @Query("From InvoiceDetails d Where d.invoiceId.invoiceId In ?1")
    List<InvoiceDetails> findAllByInvoiceIdIn(List<Integer> invoiceIds);
}
