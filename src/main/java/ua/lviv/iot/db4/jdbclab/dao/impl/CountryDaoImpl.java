package ua.lviv.iot.db4.jdbclab.dao.impl;

import ua.lviv.iot.db4.jdbclab.dao.CountryDao;
import ua.lviv.iot.db4.jdbclab.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryDaoImpl implements CountryDao {
    private static final String FIND_ALL = "SELECT * FROM country";
    private static final String CREATE = "INSERT country(name) VALUES (?)";
    private static final String UPDATE = "UPDATE country SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM country WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM country WHERE id=?";
    private static final String FIND_BY_COUNTRY_NAME = "SELECT * FROM country WHERE name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Country.class));
    }

    @Override
    public Optional<Country> findById(Integer id) {
        Optional<Country> сountry;
        try {
        	сountry = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Country.class), id));
        } catch (EmptyResultDataAccessException e) {
        	сountry = Optional.empty();
        }
        return сountry;
    }

    @Override
    public int create(Country сountry) {
        return jdbcTemplate.update(CREATE, сountry.getName());
    }

    @Override
    public int update(Integer id, Country сountry) {
        return jdbcTemplate.update(UPDATE, сountry.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Country> findByCountryName(String сountryName) {
        Optional<Country> сountry;
        try {
        	сountry = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_COUNTRY_NAME,
                    BeanPropertyRowMapper.newInstance(Country.class), сountryName));
        } catch (EmptyResultDataAccessException e) {
        	сountry = Optional.empty();
        }
        return сountry;
    }

}
