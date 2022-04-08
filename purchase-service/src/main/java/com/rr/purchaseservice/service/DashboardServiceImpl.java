package com.rr.purchaseservice.service;

import com.rr.purchaseservice.model.ChartResponse;
import com.rr.purchaseservice.model.Payment;
import com.rr.purchaseservice.model.Purchase;
import com.rr.purchaseservice.repository.PurchaseRepository;
import com.rr.purchaseservice.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public List<ChartResponse> getPurchasePaymentStats(int seasonId) {
        List<Purchase> purchase = purchaseRepository.findBySeasonId(seasonId);
        double totalBasePurchaseAmount = purchase.stream().map(Purchase::getPurchaseAmount)
                .reduce(0.0f, Float::sum);
        double totalTaxAmount = purchase.stream().map(Purchase::getTaxAmount)
                .reduce(0.0f, Float::sum);
        double totalDiscAmount = purchase.stream().map(Purchase::getDiscountAmount)
                .reduce(0.0f, Float::sum);
        double totalExtraDiscAmount = purchase.stream().map(Purchase::getExtraDiscountAmount)
                .reduce(0.0f, Float::sum);
        double totalAbsolutePurchaseAmount = totalBasePurchaseAmount + totalTaxAmount;
        double totalDiscountAmount = totalDiscAmount + totalExtraDiscAmount;
        double totalPurchaseAfterDisc = totalAbsolutePurchaseAmount - totalDiscAmount - totalExtraDiscAmount;
        double totalPaymentAmount = 0;
        for (List<Payment> payment : purchase.stream().map(Purchase::getPayments).collect(Collectors.toList())) {
            totalPaymentAmount += payment.stream().map(Payment::getAmount).reduce(0.0f, Float::sum);
        }
        List<ChartResponse> chartResponseList = new ArrayList<>();
        chartResponseList.add(ChartResponse.builder()
                .name("Total Purchase Amount")
                .value(totalAbsolutePurchaseAmount)
                .build());
        chartResponseList.add(ChartResponse.builder()
                .name("Total Disc. Amount")
                .value(totalDiscountAmount)
                .build());
        chartResponseList.add(ChartResponse.builder()
                .name("Total Purchase Amount (After Disc)")
                .value(totalPurchaseAfterDisc)
                .build());
        chartResponseList.add(ChartResponse.builder()
                .name("Total Payment Amount")
                .value(totalPaymentAmount)
                .build());
        chartResponseList.add(ChartResponse.builder()
                .name("Total Payment Pending")
                .value(totalPurchaseAfterDisc - totalPaymentAmount)
                .build());
        return chartResponseList;
    }
}
