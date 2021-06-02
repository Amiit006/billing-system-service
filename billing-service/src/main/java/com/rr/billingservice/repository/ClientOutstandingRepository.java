package com.rr.billingservice.repository;

import com.rr.billingservice.model.ClientOutstanding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOutstandingRepository extends JpaRepository<ClientOutstanding, Integer> {
}
