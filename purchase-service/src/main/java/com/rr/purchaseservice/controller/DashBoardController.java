package com.rr.purchaseservice.controller;

import com.rr.purchaseservice.model.ChartResponse;
import com.rr.purchaseservice.model.Purchase;
import com.rr.purchaseservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase/dashboard")
public class DashBoardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping()
    public ResponseEntity<?> getPurchasePaymentStats(@RequestParam("seasonId") int seasonId) {
        List<ChartResponse> lister = dashboardService.getPurchasePaymentStats(seasonId);
        return new ResponseEntity<>(lister, HttpStatus.OK);
    }
}
