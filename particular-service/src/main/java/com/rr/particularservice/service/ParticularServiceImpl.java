package com.rr.particularservice.service;

import com.rr.particularservice.model.Particular;
import com.rr.particularservice.repository.ParticularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticularServiceImpl implements ParticularService {

    @Autowired
    private ParticularRepository particularRepository;

    @Override
    public List<Particular> getAllParticulars() {
        return particularRepository.findAll();
    }

    @Override
    public Particular createParticular() {
        return null;
    }

    @Override
    public Particular updateParticulars() {
        return null;
    }

    @Override
    public boolean deleteParticulars() {
        return false;
    }
}
