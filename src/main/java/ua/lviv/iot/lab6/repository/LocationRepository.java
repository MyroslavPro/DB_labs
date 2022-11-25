package ua.lviv.iot.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.lab6.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	List<Location> findLocationsByCountryId(Integer contryId);
}
