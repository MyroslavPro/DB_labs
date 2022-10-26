package ua.lviv.iot.db4.jdbclab.dao;

import ua.lviv.iot.db4.jdbclab.models.Country;

import java.util.Optional;

public interface CountryDao extends GeneralDao<Country, Integer> {
    Optional<Country> findByCountryName(String countryName);
}
