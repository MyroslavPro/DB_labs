package ua.lviv.iot.lab5backend.repository;

import ua.lviv.iot.lab5backend.domain.Location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	List<Location> findLocationsByCountryId(Integer contryId);
}
