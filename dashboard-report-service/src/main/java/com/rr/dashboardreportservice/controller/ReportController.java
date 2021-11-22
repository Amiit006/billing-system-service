package com.rr.dashboardreportservice.controller;

import com.rr.dashboardreportservice.model.dto.*;
import com.rr.dashboardreportservice.service.ReportService;
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
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/sells")
    public ResponseEntity<?> getSellsReport(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate from_date,
                                            @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    LocalDate to_date) {
        try {
            List<SellStatsResponse> sellStatsList = reportService.getSellsReport(from_date, to_date);
            return new ResponseEntity<>(sellStatsList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.singletonMap("",""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/collection")
    public ResponseEntity<?> getCollectionsReport(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate from_date,
                                                  @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate to_date) {
        try {
             List<CollectionStatsResponse> collectionStatsResponses = reportService.getCollectionsReport(from_date, to_date);
            return new ResponseEntity<>(collectionStatsResponses, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.singletonMap("",""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client")
    public ResponseEntity<?> getClientReport(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         LocalDate from_date,
                                             @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         LocalDate to_date,
                                             @RequestParam("clientId") int clientId) {
        try {
            ClientReportResponse clientCollectionResponse = reportService.getClientReport(from_date, to_date, clientId);
            return new ResponseEntity<>(clientCollectionResponse, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.singletonMap("",""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tradebook")
    public ResponseEntity<?> getTradeBookReport(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                            LocalDate from_date,
                                                @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                            LocalDate to_date) {
        try {
            List<TradeBookResponse> tradeBookResponses = reportService.getTradeBookReport(from_date, to_date);
            return new ResponseEntity<>(tradeBookResponses, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.singletonMap("",""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/particulars")
    public ResponseEntity<?> getParticularsReport(@RequestParam("from_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate from_date,
                                                @RequestParam("to_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate to_date) {
        try {
            List<ParticularReport> particularReport = reportService.getParticularsReport(from_date, to_date);
            return new ResponseEntity<>(particularReport, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.singletonMap("",""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientOutstanding")
    public ResponseEntity<?> getClientOutstandingReport() {
        try {
            ClientOutStandingReport clientOutstandingReport = reportService.getClientOutstandingReport();
            return new ResponseEntity<>(clientOutstandingReport, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.singletonMap("",""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
