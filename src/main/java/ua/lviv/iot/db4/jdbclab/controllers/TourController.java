package ua.lviv.iot.db4.jdbclab.controllers;

import java.util.Optional;

import ua.lviv.iot.db4.jdbclab.models.Tour;

public interface TourController extends GeneralController<Tour, Integer> {
	Optional<Tour> findByTourName(String tourName);
    
    Optional<Tour> findTheCheapestTour();
}
