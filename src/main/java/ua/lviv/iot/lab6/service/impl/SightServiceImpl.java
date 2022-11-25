package ua.lviv.iot.lab6.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab6.domain.Sight;
import ua.lviv.iot.lab6.exception.SightNotFoundException;
import ua.lviv.iot.lab6.repository.SightRepository;
import ua.lviv.iot.lab6.service.SightService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class SightServiceImpl implements SightService {
    @Autowired
    private SightRepository sightRepository;
    

    @Override
    public List<Sight> findAll() {
        return sightRepository.findAll();
    }

    @Override
    public Sight findById(Integer id) {
        return sightRepository.findById(id)
        		.orElseThrow(() -> new SightNotFoundException(id));
    }

    @Override
    @Transactional
    public Sight create(Sight sight) {
        return sightRepository.save(sight);
    }

    @Override
    @Transactional
    public void update(Integer id, Sight updatedSight) {
    	Sight sight= sightRepository.findById(id)
              .orElseThrow(() -> new SightNotFoundException(id));
        
    	sight.setName(updatedSight.getName());
    	sight.setCountryId(updatedSight.getCountryId());
        sightRepository.save(sight);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Sight sight= sightRepository.findById(id)
                 .orElseThrow(() -> new SightNotFoundException(id));
    	 
    	 sightRepository.delete(sight);
    }
}
