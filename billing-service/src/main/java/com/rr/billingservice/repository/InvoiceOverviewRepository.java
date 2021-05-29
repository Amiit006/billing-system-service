package com.rr.billingservice.repository;

import com.rr.billingservice.model.Invoice;
import com.rr.billingservice.model.InvoiceOverView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceOverviewRepository extends JpaRepository<InvoiceOverView, Integer> {

    InvoiceOverView findTopByOrderByInvoiceIdDesc();
}
