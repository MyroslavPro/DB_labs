package ua.lviv.iot.lab6.service;

import java.util.List;

import ua.lviv.iot.lab6.domain.Location;

public interface LocationService extends GeneralService<Location, Integer> {
	List<Location> findLocationsByCountryId(Integer contryId);
}
