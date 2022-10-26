package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.controllers.TourController;
import ua.lviv.iot.db4.jdbclab.models.Tour;
import ua.lviv.iot.db4.jdbclab.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourControllerImpl implements TourController {
    @Autowired
    private TourService tourService;

    @Override
    public List<Tour> findAll() {
        return tourService.findAll();
    }

    @Override
    public Optional<Tour> findById(Integer id) {
        return tourService.findById(id);
    }

    @Override
    public int create(Tour tour) {
        return tourService.create(tour);
    }

    @Override
    public int update(Integer id, Tour tour) {
        return tourService.update(id, tour);
    }

    @Override
    public int delete(Integer id) {
        return tourService.delete(id);
    }
    
    @Override 
    public Optional<Tour> findByTourName(String tourName) {
        return tourService.findByTourName(tourName);
    }
    
    @Override 
    public Optional<Tour> findTheCheapestTour() {
        return tourService.findTheCheapestTour();
    }
}
