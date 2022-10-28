package ua.lviv.iot.db4.jdbclab.services;

import java.util.List;
import java.util.Optional;

import ua.lviv.iot.db4.jdbclab.models.Tour;

public interface TourService extends GeneralService<Tour, Integer> {
	Optional<Tour> findByTourName(String tourName);
    
	List<Tour> getAllAvailableTours(Double price);
}
