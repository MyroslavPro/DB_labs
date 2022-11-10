package ua.lviv.iot.lab5backend.repository;

import ua.lviv.iot.lab5backend.domain.Trip;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {	
	/*@Query("SELECT t FROM Tour t WHERE t.date_start=:date_start")
	List<Trip> findAllTripsByDateStart(@Param("date_start")Date dateStart);*/
	
	//List<Trip> findTripByDateStart(Date dateStart);
	
	
	//List<Trip> findAllTripsByDateStart(Date dateStart);
}
