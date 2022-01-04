package com.rr.purchaseservice.controller;

import com.rr.purchaseservice.exception.SeasonException;
import com.rr.purchaseservice.model.Season;
import com.rr.purchaseservice.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase/season")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping()
    public ResponseEntity<?> getAllSeasons() {
        try {
            List<Season> seasons = seasonService.findAllSeasons();
            return new ResponseEntity<List<Season>>(seasons, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<String>("result", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSeason(@RequestBody Season season) {
        try {
            Season createdSeason = seasonService.createSeason(season);
            return new ResponseEntity<Season>(createdSeason, HttpStatus.OK);
        }
        catch (SeasonException ex) {
            return new ResponseEntity<String>(ex.getException(), ex.getStatus());
        }
        catch (Exception ex) {
            return new ResponseEntity<String>("result", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateSeason(@RequestParam("seasonId") int seasonId, @RequestBody Season season) {
        try {
            Season createdSeason = seasonService.updateSeason(seasonId,season);
            return new ResponseEntity<Season>(createdSeason, HttpStatus.OK);
        }
        catch (SeasonException ex) {
            return new ResponseEntity<String>(ex.getException(), ex.getStatus());
        }
        catch (Exception ex) {
            return new ResponseEntity<String>("result", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
