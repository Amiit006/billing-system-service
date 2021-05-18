package com.rr.clientservice.service;

import com.rr.clientservice.exception.ClientException;
import com.rr.clientservice.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client createClient(Client client) throws ClientException;
    Client updateClient(int id, Client client) throws ClientException;
    boolean deleteClient(int id);
}
