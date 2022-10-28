package ua.lviv.iot.db4.jdbclab.dao.impl;

import ua.lviv.iot.db4.jdbclab.dao.TripDao;
import ua.lviv.iot.db4.jdbclab.models.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TripDaoImpl implements TripDao {
    private static final String FIND_ALL = "SELECT * FROM trip";
    private static final String CREATE = "INSERT trip(guide_id, tour_id, date_start, guide_salary) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE trip SET guide_id=?, tour_id=?, date_start=?, guide_salary=? WHERE id=?";
    private static final String DELETE = "DELETE FROM trip WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM trip WHERE id=?";
    private static final String FIND_BY_TRIP_DATE = "SELECT * FROM trip WHERE date_start=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Trip> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Trip.class));
    }

    @Override
    public Optional<Trip> findById(Integer id) {
        Optional<Trip> trip;
        try {
        	trip = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Trip.class), id));
        } catch (EmptyResultDataAccessException e) {
        	trip = Optional.empty();
        }
        return trip;
    }

    @Override
    public int create(Trip trip) {
        return jdbcTemplate.update(CREATE, trip.getGuideId(), trip.getTourId(), trip.getDateStart(), trip.getGuideId());
    }

    @Override
    public int update(Integer id, Trip trip) {
        return jdbcTemplate.update(UPDATE, trip.getGuideId(), trip.getTourId(), trip.getDateStart(), trip.getGuideId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
    
    @Override
    public Optional<Trip> findByTripDate(Date tripDate) {
        Optional<Trip> trip;
        try {
        	trip = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TRIP_DATE,
                    BeanPropertyRowMapper.newInstance(Trip.class), tripDate));
        } catch (EmptyResultDataAccessException e) {
        	trip = Optional.empty();
        }
        return trip;
    }
}
