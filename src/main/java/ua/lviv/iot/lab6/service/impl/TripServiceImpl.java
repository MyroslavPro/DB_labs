package ua.lviv.iot.lab6.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab6.domain.Trip;
import ua.lviv.iot.lab6.exception.ClientNotFoundException;
import ua.lviv.iot.lab6.exception.GuideNotFoundException;
import ua.lviv.iot.lab6.exception.TourNotFoundException;
import ua.lviv.iot.lab6.exception.TripNotFoundException;
import ua.lviv.iot.lab6.repository.ClientRepository;
import ua.lviv.iot.lab6.repository.GuideRepository;
import ua.lviv.iot.lab6.repository.TourRepository;
import ua.lviv.iot.lab6.repository.TripRepository;
import ua.lviv.iot.lab6.service.TripService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class TripServiceImpl implements TripService {
	@Autowired
    private TripRepository tripRepository;
	
	@Autowired
    private GuideRepository guideRepository;
    
    @Autowired
    private TourRepository tourRepository;    

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip findById(Integer id) {
        return tripRepository.findById(id)
        		.orElseThrow(() -> new TripNotFoundException(id));
    }
    
    @Override
    public List<Trip> findTripsByGuideId(Integer guideId){
    	guideRepository.findById(guideId).orElseThrow(() -> new GuideNotFoundException(guideId));
    	return tripRepository.findTripsByGuideId(guideId);
    }
    
    @Override
    public List<Trip> findTripsByTourId(Integer tourId){
    	tourRepository.findById(tourId).orElseThrow(() -> new TourNotFoundException(tourId));
    	return tripRepository.findTripsByTourId(tourId);
    }

    @Override
    @Transactional
    public Trip create(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    @Transactional
    public void update(Integer id, Trip updatedTour) {
    	Trip trip= tripRepository.findById(id)
              .orElseThrow(() -> new TripNotFoundException(id));
        
    	trip.setDateStart(updatedTour.getDateStart());
    	trip.setGuideSalary(updatedTour.getGuideSalary());
    	tripRepository.save(trip);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Trip trip= tripRepository.findById(id)
                 .orElseThrow(() -> new TripNotFoundException(id));
    	 
    	tripRepository.delete(trip);
    }
}
