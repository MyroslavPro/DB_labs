package ua.lviv.iot.lab5backend.repository;

import ua.lviv.iot.lab5backend.domain.Tour;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
	List<Tour> findToursByLanguageId(Integer languageId);
	List<Tour> findToursByLocationId(Integer locationId);
	
	@Query("SELECT t FROM Tour t WHERE t.price<=:price")
	List<Tour> findAvailableToursbyPrice (@Param("price")Double price);	
}
