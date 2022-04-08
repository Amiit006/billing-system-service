package com.rr.purchaseservice.service;

import com.rr.purchaseservice.exception.PurchaseException;
import com.rr.purchaseservice.model.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> getAllPurchase();
    List<Purchase> getPurchasesBySeason(int seasonId);
    Purchase createPurchase(int seasonId, Purchase purchase) throws PurchaseException;
    Purchase updatePurchase(int seasonId, int purchaseId, Purchase purchase) throws PurchaseException;
    boolean deletePurchase(int purchaseId) throws PurchaseException;
}
