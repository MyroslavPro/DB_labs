package ua.lviv.iot.lab5backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab5backend.domain.Location;
import ua.lviv.iot.lab5backend.exception.CountryNotFoundException;
import ua.lviv.iot.lab5backend.exception.LocationNotFoundException;

import ua.lviv.iot.lab5backend.repository.CountryRepository;
import ua.lviv.iot.lab5backend.repository.LocationRepository;
import ua.lviv.iot.lab5backend.service.LocationService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id)
        		.orElseThrow(() -> new LocationNotFoundException(id));
    }
    
    @Override
    public List<Location> findLocationsByCountryId(Integer contryId) {
        
    	countryRepository.findById(contryId)
		.orElseThrow(() -> new CountryNotFoundException(contryId));
    	
    	return locationRepository.findLocationsByCountryId(contryId);
    }

    @Override
    @Transactional
    public Location create(Location location) {
        return locationRepository.save(location);
    }

    @Override
    @Transactional
    public void update(Integer id, Location updatedLocation) {
    	Location location= locationRepository.findById(id)
              .orElseThrow(() -> new LocationNotFoundException(id));
        
    	location.setName(updatedLocation.getName());
        locationRepository.save(location);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Location location= locationRepository.findById(id)
                 .orElseThrow(() -> new LocationNotFoundException(id));
    	 
    	 locationRepository.delete(location);
    }
}
