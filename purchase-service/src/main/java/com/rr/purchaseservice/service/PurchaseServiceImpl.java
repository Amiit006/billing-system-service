package com.rr.purchaseservice.service;

import com.rr.purchaseservice.exception.PurchaseException;
import com.rr.purchaseservice.model.Purchase;
import com.rr.purchaseservice.model.Season;
import com.rr.purchaseservice.repository.PurchaseRepository;
import com.rr.purchaseservice.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SeasonRepository seasonRepository;

    public List<Purchase> getAllPurchase() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases;
    }

    @Override
    public Purchase createPurchase(int seasonId, Purchase purchase) throws PurchaseException {
        LocalDateTime now = LocalDateTime.now();
        Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new PurchaseException("Season not found", HttpStatus.NOT_FOUND));
        purchase.setSeason(season);
        purchase.setCreatedDate(now);
        purchase.setModifiedDate(now);
        Purchase createdPurchase = purchaseRepository.save(purchase);
        return createdPurchase;
    }

    @Override
    public Purchase updatePurchase(int seasonId, int purchaseId, Purchase purchase) throws PurchaseException {
        return null;
    }

    @Override
    public boolean deletePurchase(int purchaseId) throws PurchaseException {
        Purchase existingPurchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseException("Purchase not found", HttpStatus.NOT_FOUND));
        purchaseRepository.delete(existingPurchase);
        return true;
    }
}
