package com.rr.dashboardreportservice.controller;

import com.rr.dashboardreportservice.service.TopContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/dashboard")
public class TopContentController {

    @Autowired
    TopContentService topContentService;

    @GetMapping("/topProduct")
    public ResponseEntity<?> getTopSellingProduct(@RequestParam("topCount") int topCount ) {
        try{
            return new ResponseEntity<>(topContentService.getTopSellingProducts(topCount), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/topBuyer")
    public ResponseEntity<?> getTopBuyer(@RequestParam("topCount") int topCount ) {
        try{
            return new ResponseEntity<>(topContentService.getTopBuyer(topCount), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sellCollectionStats")
    public ResponseEntity<?> getSellCollectionStats(@RequestParam("year") int year ) {
        try{
            return new ResponseEntity<>(topContentService.getSellCollectionStats(year), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/monthlySellStats")
    public ResponseEntity<?> getMonthlySellStats() {
        try{
            return new ResponseEntity<>(topContentService.getMonthlySellStats(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap("error", "Error while fetching data!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
