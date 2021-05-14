package com.rr.particularservice.service;

import com.rr.particularservice.model.Particular;

import java.util.List;

public interface ParticularService {

    List<Particular> getAllParticulars();
    Particular createParticular();
    Particular updateParticulars();
    boolean deleteParticulars();

}
