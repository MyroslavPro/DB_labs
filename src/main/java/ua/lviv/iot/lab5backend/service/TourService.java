package ua.lviv.iot.lab5backend.service;

import java.util.List;

import ua.lviv.iot.lab5backend.domain.Tour;

public interface TourService extends GeneralService<Tour, Integer> {
	List<Tour> findToursByLanguageId(Integer languageId);
	List<Tour> findToursByLocationId(Integer locationId);
	List<Tour> findAvailableToursbyPrice (Double price);
}
