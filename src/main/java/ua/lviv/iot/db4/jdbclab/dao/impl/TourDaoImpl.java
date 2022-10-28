package ua.lviv.iot.db4.jdbclab.dao.impl;

import ua.lviv.iot.db4.jdbclab.dao.TourDao;
import ua.lviv.iot.db4.jdbclab.models.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TourDaoImpl implements TourDao {
    private static final String FIND_ALL = "SELECT * FROM tour";
    private static final String CREATE = "INSERT tour(name, price, tour_days, description, language_id, location_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE tour SET name=?, price=?, tour_days=?, description=?, language_id=?, location_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM tour WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM tour WHERE id=?";
    private static final String FIND_BY_TOUR_NAME = "SELECT * FROM tour WHERE name=?";
    private static final String FIND_THE_AVAILABLE_TOURS = "SELECT * FROM tour WHERE price<?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tour> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Tour.class));
    }

    @Override
    public Optional<Tour> findById(Integer id) {
        Optional<Tour> tour;
        try {
        	tour = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Tour.class), id));
        } catch (EmptyResultDataAccessException e) {
        	tour = Optional.empty();
        }
        return tour;
    }

    @Override
    public int create(Tour tour) {
        return jdbcTemplate.update(CREATE, tour.getName(), tour.getPrice(),tour.getTourDays(),tour.getDescription() ,tour.getLanguageId(), tour.getLocationId());
    }

    @Override
    public int update(Integer id, Tour tour) {
        return jdbcTemplate.update(UPDATE, tour.getName(), tour.getPrice(),tour.getTourDays(),tour.getDescription() ,tour.getLanguageId(), tour.getLocationId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Tour> findByTourName(String tourName) {
        Optional<Tour> tour;
        try {
        	tour = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TOUR_NAME,
                    BeanPropertyRowMapper.newInstance(Tour.class), tourName));
        } catch (EmptyResultDataAccessException e) {
        	tour = Optional.empty();
        }
        return tour;
    }
    
    // Return List of Available TOURS
    @Override
    public List<Tour> getAllAvailableTours(Double price) {
    	return jdbcTemplate.query(FIND_THE_AVAILABLE_TOURS, BeanPropertyRowMapper.newInstance(Tour.class),price);
//        Optional<Tour> tour;
//        try {
//        	tour = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_THE_AVAILABLE_TOURS,
//                    BeanPropertyRowMapper.newInstance(Tour.class)));
//        } catch (EmptyResultDataAccessException e) {
//        	tour = Optional.empty();
//        }
//        return tour;
    }

}
