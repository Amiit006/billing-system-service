package com.rr.clientservice.controller;

import com.rr.clientservice.model.Client;
import com.rr.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> result = clientService.getAllClients();
            return new ResponseEntity<List<Client>>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("result", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public void createClient(@RequestBody Client client) {

    }

    @PutMapping("{id}")
    public void updateClient(int id, Client client) {

    }

//    @PutMapping("{id}")
//    public void deleteClient(int id) {
//
//    }
}
