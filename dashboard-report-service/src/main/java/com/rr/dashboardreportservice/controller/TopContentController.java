package com.rr.dashboardreportservice.controller;

import com.rr.dashboardreportservice.model.dto.ChartResponse;
import com.rr.dashboardreportservice.model.dto.ClientReportResponse;
import com.rr.dashboardreportservice.service.TopContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class TopContentController {

    @Autowired
    TopContentService topContentService;

    @GetMapping("/topProduct")
    public ResponseEntity<?> getTopSellingProduct(@RequestParam("topCount") int topCount) {
        try {
            return new ResponseEntity<>(topContentService.getTopSellingProducts(topCount), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/topBuyer")
    public ResponseEntity<?> getTopBuyer(@RequestParam("topCount") int topCount) {
        try {
            return new ResponseEntity<>(topContentService.getTopBuyer(topCount), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sellCollectionStats")
    public ResponseEntity<?> getSellCollectionStats(@RequestParam("year") int year) {
        try {
            return new ResponseEntity<>(topContentService.getSellCollectionStats(year), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/monthlySellStats")
    public ResponseEntity<?> getMonthlySellStats() {
        try {
            return new ResponseEntity<>(topContentService.getMonthlySellStats(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sells")
    public ResponseEntity<?> getSellStats(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate from_date,
                                          @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate to_date) {
        try {
            return new ResponseEntity<>(topContentService.getSellStats(from_date, to_date), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/collection")
    public ResponseEntity<?> getCollectionStats(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate from_date,
                                                @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate to_date) {
        try {
            return new ResponseEntity<>(topContentService.getCollectionStats(from_date, to_date), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client")
    public ResponseEntity<?> getSellCollectionStatsByClientId(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      LocalDate from_date,
                                                              @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      LocalDate to_date,
                                                              @RequestParam("clientId") int clientId) {
        try {
            return new ResponseEntity<>(topContentService.getSellCollectionStatsByClientId(from_date, to_date, clientId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientOutstanding")
    public ResponseEntity<?> getClientOutstanding(@RequestParam("clientId") int clientId) {
        try {
            List<ChartResponse> clientCollectionResponse = topContentService.getClientOutstanding(clientId);
            return new ResponseEntity<>(clientCollectionResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
