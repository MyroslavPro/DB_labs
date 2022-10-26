package ua.lviv.iot.db4.jdbclab.services.impl;

import ua.lviv.iot.db4.jdbclab.dao.CountryDao;
import ua.lviv.iot.db4.jdbclab.models.Country;
import ua.lviv.iot.db4.jdbclab.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao сountryDao;

    @Override
    public List<Country> findAll() {
        return сountryDao.findAll();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return сountryDao.findById(id);
    }

    @Override
    public int create(Country сountry) {
        return сountryDao.create(сountry);
    }

    @Override
    public int update(Integer id, Country сountry) {
        return сountryDao.update(id, сountry);
    }

    @Override
    public int delete(Integer id) {
        return сountryDao.delete(id);
    }

    @Override
    public Optional<Country> findByCountryName(String сountryName) {
        return сountryDao.findByCountryName(сountryName);
    }
}
