package ua.lviv.iot.db4.jdbclab.services.impl;

import ua.lviv.iot.db4.jdbclab.dao.TripDao;
import ua.lviv.iot.db4.jdbclab.models.Trip;
import ua.lviv.iot.db4.jdbclab.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripDao tripDao;

    @Override
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Override
    public Optional<Trip> findById(Integer id) {
        return tripDao.findById(id);
    }

    @Override
    public int create(Trip trip) {
        return tripDao.create(trip);
    }

    @Override
    public int update(Integer id, Trip trip) {
        return tripDao.update(id, trip);
    }

    @Override
    public int delete(Integer id) {
        return tripDao.delete(id);
    }

    @Override
    public Optional<Trip> findByTripDate(Date tripDate) {
        return tripDao.findByTripDate(tripDate);
    }
}
