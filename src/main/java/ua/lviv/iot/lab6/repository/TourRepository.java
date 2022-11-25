package ua.lviv.iot.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.lab6.domain.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
	List<Tour> findToursByLanguageId(Integer languageId);
	List<Tour> findToursByLocationId(Integer locationId);
	
	@Query("SELECT t FROM Tour t WHERE t.price<=:price")
	List<Tour> findAvailableToursByPrice (@Param("price")Double price);	
}
