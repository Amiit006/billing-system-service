package com.rr.particularservice.service;

import com.rr.particularservice.exception.ParticularException;
import com.rr.particularservice.model.Particular;
import com.rr.particularservice.model.ParticularDto;

import java.util.List;

public interface ParticularService {

    List<Particular> getAllParticulars();
    Particular createParticular(String particularName, float discountPercentage) throws ParticularException;
    List<Particular> createMultipleParticular(List<ParticularDto> particularsList) throws ParticularException;
    Particular updateParticular(int id, Particular particular) throws ParticularException;
    boolean deleteParticular();

}
