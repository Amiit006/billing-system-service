package com.rr.purchaseservice.controller;

import com.rr.purchaseservice.exception.PurchaseException;
import com.rr.purchaseservice.model.Purchase;
import com.rr.purchaseservice.repository.PurchaseRepository;
import com.rr.purchaseservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestParam("seasonId") int seasonId, @RequestBody Purchase purchase) {
        try {
            Purchase purchase1 = purchaseService.createPurchase(seasonId, purchase);
            return new ResponseEntity<>(purchase1, HttpStatus.CREATED);
        } catch (PurchaseException e) {
            return new ResponseEntity<>(e.getException(),e.getStatus());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getPurchasesBySeason(@RequestParam("seasonId") int seasonId) {
        List<Purchase> purchases =  purchaseService.getPurchasesBySeason(seasonId);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/purchases")
    public ResponseEntity<?> getPurchases() {
        List<Purchase> purchases =  purchaseService.getAllPurchase();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }



    @DeleteMapping()
    public ResponseEntity<?> deletePurchases(@RequestParam("purchaseId") int purchaseId) {
        boolean result = false;
        try {
            result = purchaseService.deletePurchase(purchaseId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (PurchaseException e) {
            return new ResponseEntity<>(e.getException(),e.getStatus());
        }
    }
}
