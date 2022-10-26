package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.controllers.TripController;
import ua.lviv.iot.db4.jdbclab.models.Trip;
import ua.lviv.iot.db4.jdbclab.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TripControllerImpl implements TripController {
    @Autowired
    private TripService tripService;

    @Override
    public List<Trip> findAll() {
        return tripService.findAll();
    }

    @Override
    public Optional<Trip> findById(Integer id) {
        return tripService.findById(id);
    }

    @Override
    public int create(Trip trip) {
        return tripService.create(trip);
    }

    @Override
    public int update(Integer id, Trip trip) {
        return tripService.update(id, trip);
    }

    @Override
    public int delete(Integer id) {
        return tripService.delete(id);
    }

    @Override
    public Optional<Trip> findByTripDate(Date tripDate) {
        return tripService.findByTripDate(tripDate);
    }
}
