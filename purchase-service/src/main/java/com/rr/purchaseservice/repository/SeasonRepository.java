package com.rr.purchaseservice.repository;

import com.rr.purchaseservice.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    Season findBySeasonName(String seasonName);
}
