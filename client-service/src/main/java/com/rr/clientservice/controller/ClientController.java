package com.rr.clientservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rr.clientservice.exception.ClientException;
import com.rr.clientservice.model.Client;
import com.rr.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/clients")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ObjectMapper mapper;

    private final Map<String, Object> json = new HashMap<>();

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> result = clientService.getAllClients();
            json.put("Hello", "World");
            String value = mapper.writeValueAsString(json);
            log.info(value);
            return new ResponseEntity<List<Client>>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("result", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client result = clientService.createClient(client);
            return new ResponseEntity<Client>(result, HttpStatus.OK);
        } catch (ClientException ex) {
            return new ResponseEntity<String>(ex.getException(), ex.getStatus());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") int id, @RequestBody Client client) {
        try {
            Client result = clientService.updateClient(id,client);
            return new ResponseEntity<Client>(result, HttpStatus.OK);
        } catch (ClientException ex) {
            return new ResponseEntity<String>(ex.getException(), ex.getStatus());
        }
    }

//    @PutMapping("{id}")
//    public void deleteClient(int id) {
//
//    }
}
