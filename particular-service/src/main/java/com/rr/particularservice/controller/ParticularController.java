package com.rr.particularservice.controller;

import com.rr.particularservice.model.Particular;
import com.rr.particularservice.service.ParticularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/particulars")
@CrossOrigin("*")
public class ParticularController {

    @Autowired
    private ParticularService particularService;

    @GetMapping
    public ResponseEntity<?> getAllParticulars() {
        try {
            List<Particular> particulars = particularService.getAllParticulars();
            return new ResponseEntity<>(particulars, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("particulars", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
