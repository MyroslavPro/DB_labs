package ua.lviv.iot.db4.jdbclab.dao;

import ua.lviv.iot.db4.jdbclab.models.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDao extends GeneralDao<Tour, Integer> {
    Optional<Tour> findByTourName(String tourName);
    
    List<Tour> getAllAvailableTours(Double price);
}
