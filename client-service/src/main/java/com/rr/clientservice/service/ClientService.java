package com.rr.clientservice.service;

import com.rr.clientservice.exception.ClientException;
import com.rr.clientservice.model.Client;
import com.rr.clientservice.model.dto.ClientMinDto;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(int clientId) throws ClientException;
    Client createClient(Client client) throws ClientException;
    Client updateClient(int id, Client client) throws ClientException;
    boolean deleteClient(int id);
    boolean isClientPresent(Client client);
    boolean isClientPresentByClientId(int clientId) throws ClientException;

    List<ClientMinDto> getClientsByClientIds(List<Integer> clientIds) throws ClientException;

}
