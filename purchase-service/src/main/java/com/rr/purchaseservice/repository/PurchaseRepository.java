package com.rr.purchaseservice.repository;

import com.rr.purchaseservice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query("FROM Purchase p where p.season.seasonId=:seasonId AND p.purchaseId=:purchaseId")
    Optional<Purchase> findBySeasonIdAndPurchaseId(int seasonId, int purchaseId);
}
