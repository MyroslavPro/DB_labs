package ua.lviv.iot.db4.jdbclab.services.impl;

import ua.lviv.iot.db4.jdbclab.dao.TourDao;
import ua.lviv.iot.db4.jdbclab.models.Tour;
import ua.lviv.iot.db4.jdbclab.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourDao tourDao;

    @Override
    public List<Tour> findAll() {
        return tourDao.findAll();
    }

    @Override
    public Optional<Tour> findById(Integer id) {
        return tourDao.findById(id);
    }

    @Override
    public int create(Tour tour) {
        return tourDao.create(tour);
    }

    @Override
    public int update(Integer id, Tour tour) {
        return tourDao.update(id, tour);
    }

    @Override
    public int delete(Integer id) {
        return tourDao.delete(id);
    }
    
    @Override 
    public Optional<Tour> findByTourName(String tourName) {
        return tourDao.findByTourName(tourName);
    }
    
    @Override 
    public List<Tour> getAllAvailableTours(Double price) {
        return tourDao.getAllAvailableTours(price);
    }
}
