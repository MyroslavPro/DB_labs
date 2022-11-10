package ua.lviv.iot.lab5backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab5backend.domain.Country;
import ua.lviv.iot.lab5backend.exception.CountryNotFoundException;

import ua.lviv.iot.lab5backend.repository.CountryRepository;
import ua.lviv.iot.lab5backend.service.CountryService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository сountryRepository;

    @Override
    public List<Country> findAll() {
        return сountryRepository.findAll();
    }

    @Override
    public Country findById(Integer id) {
        return сountryRepository.findById(id)
        		.orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    @Transactional
    public Country create(Country сountry) {
        return сountryRepository.save(сountry);
    }

    @Override
    @Transactional
    public void update(Integer id, Country updatedCountry) {
        Country сountry = сountryRepository.findById(id)
              .orElseThrow(() -> new CountryNotFoundException(id));
        
        сountry.setName(updatedCountry.getName());
        сountryRepository.save(сountry);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	 Country сountry = сountryRepository.findById(id)
                 .orElseThrow(() -> new CountryNotFoundException(id));
    	 
    	 сountryRepository.delete(сountry);
    }
}
