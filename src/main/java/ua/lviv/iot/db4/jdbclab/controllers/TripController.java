package ua.lviv.iot.db4.jdbclab.controllers;

import ua.lviv.iot.db4.jdbclab.models.Trip;

import java.sql.Date;
import java.util.Optional;

public interface TripController extends GeneralController<Trip, Integer> {
	Optional<Trip> findByTripDate(Date tripDate);
}
