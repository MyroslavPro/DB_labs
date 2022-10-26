package ua.lviv.iot.db4.jdbclab.dao;

import ua.lviv.iot.db4.jdbclab.models.Trip;

import java.sql.Date;
import java.util.Optional;

public interface TripDao extends GeneralDao<Trip, Integer> {
    Optional<Trip> findByTripDate(Date tripDate);
}
