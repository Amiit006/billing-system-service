package com.rr.particularservice.controller;

import com.rr.particularservice.exception.ParticularException;
import com.rr.particularservice.model.Particular;
import com.rr.particularservice.service.ParticularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/particulars")
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

    @PostMapping
    public ResponseEntity<?> createSingleParticular(@RequestBody Particular particular) {
        try {
            String particularName = particular.getParticularName();
            int discountPercentage = particular.getDiscountPercentage();
            Particular particular1 = particularService.createParticular(particularName, discountPercentage);
            return new ResponseEntity<Particular>(particular1 , HttpStatus.CREATED);
        } catch (ParticularException exception) {
            return new ResponseEntity<>(exception.getException(), exception.getStatus());
        }
    };

    @PostMapping("/bulkCreate")
    public ResponseEntity<?> createMultipleParticular(@RequestBody List<String> particulars) {
        try {
            List<Particular> result =  particularService.createMultipleParticular(particulars);
            return new ResponseEntity<>(result , HttpStatus.CREATED);
        } catch (ParticularException exception) {
            return new ResponseEntity<>(exception.getException(), exception.getStatus());
        }
    };

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSingleParticular(@PathVariable("id") int id, @RequestBody Particular particular) {
        try {
            Particular particular1 = particularService.updateParticular(id, particular);
            return new ResponseEntity<>("Successfully updated.", HttpStatus.CREATED);
        } catch (ParticularException exception) {
            return new ResponseEntity<>(exception.getException(), exception.getStatus());
        }
    };


}
