package ua.lviv.iot.lab6.service;

import java.util.List;

import ua.lviv.iot.lab6.domain.Tour;

public interface TourService extends GeneralService<Tour, Integer> {
	List<Tour> findToursByLanguageId(Integer languageId);
	List<Tour> findToursByLocationId(Integer locationId);
	List<Tour> findAvailableToursByPrice (Double price);
}
