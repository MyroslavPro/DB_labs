package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.controllers.CountryController;
import ua.lviv.iot.db4.jdbclab.models.Country;
import ua.lviv.iot.db4.jdbclab.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CountryControllerImpl implements CountryController {
    @Autowired
    private CountryService countryService;

    @Override
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return countryService.findById(id);
    }

    @Override
    public int create(Country country) {
        return countryService.create(country);
    }

    @Override
    public int update(Integer id, Country country) {
        return countryService.update(id, country);
    }

    @Override
    public int delete(Integer id) {
        return countryService.delete(id);
    }

    @Override
    public Optional<Country> findByCountryName(String countryName) {
        return countryService.findByCountryName(countryName);
    }
}
