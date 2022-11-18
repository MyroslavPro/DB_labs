package ua.lviv.iot.lab5backend.service;

import java.util.List;

import ua.lviv.iot.lab5backend.domain.Location;

public interface LocationService extends GeneralService<Location, Integer> {
	List<Location> findLocationsByCountryId(Integer contryId);
}
