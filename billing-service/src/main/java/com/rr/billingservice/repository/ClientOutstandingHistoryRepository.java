package com.rr.billingservice.repository;

import com.rr.billingservice.model.ClientOutstanding;
import com.rr.billingservice.model.ClientOutstandingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOutstandingHistoryRepository extends JpaRepository<ClientOutstandingHistory, Integer> {
}
