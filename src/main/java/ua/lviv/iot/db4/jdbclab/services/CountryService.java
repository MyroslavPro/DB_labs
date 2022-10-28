package ua.lviv.iot.db4.jdbclab.services;

import ua.lviv.iot.db4.jdbclab.models.Country;

import java.util.Optional;

public interface CountryService extends GeneralService<Country, Integer> {
    Optional<Country> findByCountryName(String сountryName);
}
