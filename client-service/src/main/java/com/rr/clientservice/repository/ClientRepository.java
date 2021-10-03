package com.rr.clientservice.repository;

import com.rr.clientservice.model.Client;
import com.rr.clientservice.model.dto.ClientMinDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<List<Client>> findByClientName(String clientName);

    Client findByClientIdAndClientNameAndMobile(int clientId, String clientName, String mobile);

    @Query("Select new com.rr.clientservice.model.dto.ClientMinDto(c.clientId, c.clientName, c.mobile, c.email) From Client c Where c.clientId In :clientId")
    Optional<List<ClientMinDto>> findByClientIdIn(List<Integer> clientId);
}
