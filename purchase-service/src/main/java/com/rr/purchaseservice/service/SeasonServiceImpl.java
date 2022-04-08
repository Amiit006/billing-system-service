package com.rr.purchaseservice.service;

import com.rr.purchaseservice.exception.SeasonException;
import com.rr.purchaseservice.model.Season;
import com.rr.purchaseservice.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public List<Season> findAllSeasons() {
        return seasonRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Season::getStartDate))
                .collect(Collectors.toList());
    }

    @Override
    public Season createSeason(Season season) throws SeasonException {
        LocalDateTime now = LocalDateTime.now();

        if(season.getStartDate().isAfter(season.getEndDate()))
            throw new SeasonException("End Date can't be before start Date", HttpStatus.BAD_REQUEST);
        if(seasonRepository.findBySeasonName(season.getSeasonName()) != null)
            throw new SeasonException("Season Name already exists", HttpStatus.BAD_REQUEST);
        validateSeasonPriorCreation(season);
        Season season1 = Season.builder()
                        .seasonName(season.getSeasonName())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .createdDate(now)
                .modifiedDate(now)
                        .build();
        seasonRepository.save(season1);
        return season1;
    }

    private void validateSeasonPriorCreation(Season newSeason) throws SeasonException{
        List<Season> existingSeasons = findAllSeasons();
        for (Season existingSeason: existingSeasons) {
            if(isWithinRange(newSeason.getStartDate(), existingSeason.getStartDate(), existingSeason.getEndDate()))
                throw new SeasonException("Season duration collides with other season", HttpStatus.BAD_REQUEST);
            if(isWithinRange(newSeason.getEndDate(), existingSeason.getStartDate(), existingSeason.getEndDate()))
                throw new SeasonException("Season duration collides with other season", HttpStatus.BAD_REQUEST);
            if(newSeason.getEndDate().isBefore(existingSeason.getEndDate())) {
                if(newSeason.getStartDate().isAfter(existingSeason.getStartDate())
                    || newSeason.getEndDate().isAfter(existingSeason.getStartDate())
                    || newSeason.getEndDate().isEqual(existingSeason.getEndDate())) {
                    throw new SeasonException("Season duration collides with other season", HttpStatus.BAD_REQUEST);
                }
            } else {
                if(newSeason.getStartDate().isBefore(existingSeason.getStartDate())
                        || (newSeason.getStartDate().isAfter(existingSeason.getStartDate())
                            && newSeason.getStartDate().isBefore(existingSeason.getEndDate()))) {
                    throw new SeasonException("Season duration collides with other season", HttpStatus.BAD_REQUEST);
                }
            }


        }
    }

    private boolean isWithinRange(LocalDate testDate, LocalDate startDate, LocalDate endDate) {
        return testDate.isEqual(startDate) ||
                testDate.isEqual(endDate) ||
                (testDate.isAfter(startDate) && testDate.isBefore(endDate));
    }

    @Override
    public Season updateSeason(int seasonId, Season season) throws SeasonException {
        LocalDateTime now = LocalDateTime.now();
        if(season.getStartDate().isAfter(season.getEndDate()))
            throw new SeasonException("End Date can't be before start Date", HttpStatus.BAD_REQUEST);

        Season season1 = seasonRepository.findById(seasonId).orElseThrow(() -> new SeasonException("Season not present", HttpStatus.NOT_FOUND));

        if(season1.getSeasonName().equalsIgnoreCase(season.getSeasonName()))
            throw new SeasonException("Season Name already exists", HttpStatus.BAD_REQUEST);
        season1.setSeasonName(season.getSeasonName());
        season1.setStartDate(season.getStartDate());
        season1.setEndDate(season.getEndDate());
        season1.setModifiedDate(now);
        seasonRepository.saveAndFlush(season1);
        return season1;
    }
}
