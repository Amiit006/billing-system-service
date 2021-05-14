package com.rr.particularservice.repository;

import com.rr.particularservice.model.Particular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticularRepository extends JpaRepository<Particular, Integer> {
}
