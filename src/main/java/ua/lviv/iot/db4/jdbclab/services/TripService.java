package ua.lviv.iot.db4.jdbclab.services;

import ua.lviv.iot.db4.jdbclab.models.Trip;

import java.sql.Date;
import java.util.Optional;

public interface TripService extends GeneralService<Trip, Integer> {
	Optional<Trip> findByTripDate(Date tripDate);
}
