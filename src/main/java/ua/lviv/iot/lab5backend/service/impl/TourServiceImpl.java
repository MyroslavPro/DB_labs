package ua.lviv.iot.lab5backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab5backend.domain.Tour;
import ua.lviv.iot.lab5backend.exception.LanguageNotFoundException;
import ua.lviv.iot.lab5backend.exception.TourNotFoundException;
import ua.lviv.iot.lab5backend.exception.LocationNotFoundException;
import ua.lviv.iot.lab5backend.repository.LocationRepository;
import ua.lviv.iot.lab5backend.repository.TourRepository;
import ua.lviv.iot.lab5backend.repository.LanguageRepository;
import ua.lviv.iot.lab5backend.service.TourService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class TourServiceImpl implements TourService {
	@Autowired
    private TourRepository tourRepository;
	
	@Autowired
    private LanguageRepository languageRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour findById(Integer id) {
        return tourRepository.findById(id)
        		.orElseThrow(() -> new TourNotFoundException(id));
    }
    
    
    @Override
    public List<Tour> findToursByLocationId(Integer locationId){
    	locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
    	return tourRepository.findToursByLocationId(locationId);
    }
    
    @Override
    public List<Tour> findToursByLanguageId(Integer languageId){
    	languageRepository.findById(languageId).orElseThrow(() -> new LanguageNotFoundException(languageId));
    	return tourRepository.findToursByLocationId(languageId);
    }
    // check
    @Override
    public List<Tour> findAvailableToursbyPrice (Double price){
    	return tourRepository.findAvailableToursbyPrice(price);
    }

    @Override
    @Transactional
    public Tour create(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    @Transactional
    public void update(Integer id, Tour updatedTour) {
    	Tour tour= tourRepository.findById(id)
              .orElseThrow(() -> new TourNotFoundException(id));
        
    	tour.setName(updatedTour.getName());
    	tour.setPrice(updatedTour.getPrice());
    	tour.setTourDays(updatedTour.getTourDays());
    	tour.setDescription(updatedTour.getDescription());
    	tourRepository.save(tour);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Tour tour= tourRepository.findById(id)
                 .orElseThrow(() -> new TourNotFoundException(id));
    	 
    	tourRepository.delete(tour);
    }
}
