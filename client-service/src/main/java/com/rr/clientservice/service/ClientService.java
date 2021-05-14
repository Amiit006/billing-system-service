package com.rr.clientservice.service;

import com.rr.clientservice.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client createClient(Client client);
    Client updateClient(int id, Client client);
    boolean deleteClient(int id);
}
