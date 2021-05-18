package com.rr.particularservice.repository;

import com.rr.particularservice.model.Particular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticularRepository extends JpaRepository<Particular, Integer> {

    Optional<Particular> findByParticularName(String particularName);
}
