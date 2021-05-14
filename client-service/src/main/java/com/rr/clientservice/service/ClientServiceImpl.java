package com.rr.clientservice.service;

import com.rr.clientservice.model.Client;
import com.rr.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return null;
    }

    @Override
    public Client updateClient(int id, Client client) {
        return null;
    }

    @Override
    public boolean deleteClient(int id) {
        return false;
    }
}
