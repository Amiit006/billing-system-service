package com.rr.billingservice.repository;

import com.rr.billingservice.model.InvoiceOverView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceOverviewRepository extends JpaRepository<InvoiceOverView, Integer> {

    InvoiceOverView findTopByOrderByInvoiceIdDesc();

    List<InvoiceOverView> findByClientId(int clientId);
}
