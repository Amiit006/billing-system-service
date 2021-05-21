package com.rr.particularservice.service;

import com.rr.particularservice.exception.ParticularException;
import com.rr.particularservice.model.Particular;
import com.rr.particularservice.repository.ParticularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParticularServiceImpl implements ParticularService {

    @Autowired
    private ParticularRepository particularRepository;

    @Override
    public List<Particular> getAllParticulars() {
        return particularRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Particular::getParticularId))
                .collect(Collectors.toList());
    }

    @Override
    public Particular createParticular(String particularName) throws ParticularException {
        Optional<Particular> result = particularRepository.findByParticularName(particularName);
        if(result.isPresent()) {
            throw new ParticularException("Particular already present: " + particularName, HttpStatus.FOUND);
        }
        Particular particular = Particular.builder().particularName(particularName).build();
        particular = particularRepository.save(particular);
        System.out.println(particular);
        return particular;
    }

    @Override
    public List<Particular> createMultipleParticular(List<String> particularsList) throws ParticularException {
        List<Particular> existingParticulars = particularRepository.findAll();
        List<String> existingParticularsList = existingParticulars.stream()
                .map(x -> x.getParticularName())
                .distinct().collect(Collectors.toList());
        List<String> difference = new ArrayList<>(particularsList);
        difference.removeAll(existingParticularsList);
        List<Particular> newParticularsList = new ArrayList<>();
        difference.forEach(x -> {
            newParticularsList.add(Particular.builder().particularName(x).build());
        });
        List<Particular> result = particularRepository.saveAll(newParticularsList);
        return result;
    }

    @Override
    public Particular updateParticular(int id, Particular particular) throws ParticularException{
        if(id != particular.getParticularId())
            throw new ParticularException("Url Id ("+ id +") and Object id ("+ particular.getParticularId() +") does not match."
                    , HttpStatus.BAD_REQUEST);
        Optional<Particular> result = particularRepository.findById(id);
        if(!result.isPresent())
            throw new ParticularException("Particular not found in DB: " + particular.getParticularName(), HttpStatus.NOT_FOUND);

        Optional<Particular> resultByName = particularRepository.findByParticularName(particular.getParticularName());
        if(result.isPresent())
            throw new ParticularException("Particular already present in DB: " + particular.getParticularName(), HttpStatus.NOT_FOUND);

        particular = particularRepository.save(particular);
        return particular;
    }

    @Override
    public boolean deleteParticular() {
        return false;
    }
}
