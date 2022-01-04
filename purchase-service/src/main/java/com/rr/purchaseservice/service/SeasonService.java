package com.rr.purchaseservice.service;

import com.rr.purchaseservice.exception.SeasonException;
import com.rr.purchaseservice.model.Season;

import java.util.List;


public interface SeasonService {
    List<Season> findAllSeasons();
    Season createSeason(Season season) throws SeasonException;
    Season updateSeason(int seasonId, Season season) throws SeasonException;
}
