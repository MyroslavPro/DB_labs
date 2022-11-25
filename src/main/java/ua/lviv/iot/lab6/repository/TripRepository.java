package ua.lviv.iot.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.lab6.domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {	
	List<Trip> findTripsByGuideId(Integer guideId);
	List<Trip> findTripsByTourId(Integer tourId);
	// List<Trip> findTripsByClientId(Integer clientId);
	
}
