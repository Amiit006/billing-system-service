package com.rr.clientservice.repository;

import com.rr.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<List<Client>> findByClientName(String clientName);
}
