package ua.lviv.iot.db4.jdbclab.controllers;

import ua.lviv.iot.db4.jdbclab.models.Country;

import java.util.Optional;

public interface CountryController extends GeneralController<Country, Integer> {
    Optional<Country> findByCountryName(String countryName);
}
