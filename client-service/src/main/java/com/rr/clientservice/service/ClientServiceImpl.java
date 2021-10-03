package com.rr.clientservice.service;

import com.rr.clientservice.exception.ClientException;
import com.rr.clientservice.model.Client;
import com.rr.clientservice.model.ClientAddress;
import com.rr.clientservice.model.dto.ClientMinDto;
import com.rr.clientservice.repository.ClientAddressRepository;
import com.rr.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientAddressRepository clientAddressRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(int clientId) throws ClientException {
        Optional<Client> result = clientRepository.findById(clientId);
        return result.orElseThrow(() -> new ClientException("Client Not Present", HttpStatus.NOT_FOUND));
    }

    @Override
    public Client createClient(Client client) throws ClientException {
        Optional<List<Client>> results = clientRepository.findByClientName(client.getClientName());
        if(results.isPresent()) {
            for (Client result : results.get()) {
                if (result.equals(client)) {
                    throw new ClientException("Client Already Present: " + client.getClientName(), HttpStatus.FOUND);
                }
            }
        }
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client updateClient(int id, Client client) throws ClientException{
        Optional<Client> result = clientRepository.findById(id);

        if(!result.isPresent())
            throw new ClientException("Client Not Present: " + client.getClientName(), HttpStatus.FOUND);

        Client toUpdate = result.get();
        toUpdate.setClientName(client.getClientName());
        toUpdate.setMobile(client.getMobile());
        toUpdate.setEmail(client.getEmail());

        toUpdate.getAddress().setStoreName(client.getAddress().getStoreName());
        toUpdate.getAddress().setAddressLine1(client.getAddress().getAddressLine1());
        toUpdate.getAddress().setAddressLine2(client.getAddress().getAddressLine2());
        toUpdate.getAddress().setCity(client.getAddress().getCity());
        toUpdate.getAddress().setState(client.getAddress().getState());
        toUpdate.getAddress().setZip(client.getAddress().getZip());

        clientRepository.save(toUpdate);
        return toUpdate;
    }

    @Override
    public boolean deleteClient(int id) {
        return false;
    }

    @Override
    public boolean isClientPresent(Client client) {
        Client client1 = clientRepository.findByClientIdAndClientNameAndMobile(client.getClientId(),
                client.getClientName(), client.getMobile());
        return client1 == null ? false : true;
    }

    @Override
    public boolean isClientPresentByClientId(int clientId) throws ClientException {
        Client c = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientException("Client Not Present", HttpStatus.NOT_FOUND));
        return true;
    }

    @Override
    public List<ClientMinDto> getClientsByClientIds(List<Integer> clientIds) throws ClientException {
        List<ClientMinDto> clients = clientRepository.findByClientIdIn(clientIds).orElseThrow(() -> new ClientException("Client Not Present", HttpStatus.NOT_FOUND));
        return clients;
    }
}
