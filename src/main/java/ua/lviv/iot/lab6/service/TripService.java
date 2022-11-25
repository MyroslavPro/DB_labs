package ua.lviv.iot.lab6.service;

import java.util.List;

import ua.lviv.iot.lab6.domain.Trip;

public interface TripService extends GeneralService<Trip, Integer> {
	List<Trip> findTripsByGuideId(Integer guideId);
	List<Trip> findTripsByTourId(Integer tourId);
	//List<Trip> findTripsByClientId(Integer clientId);
}
