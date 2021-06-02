package com.rr.billingservice.repository;

import com.rr.billingservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByClientId(int clientId);

    Optional<Payment> findTopByOrderByPaymentIdDesc();
}
