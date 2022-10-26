package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.controllers.LocationController;
import ua.lviv.iot.db4.jdbclab.models.Location;
import ua.lviv.iot.db4.jdbclab.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationControllerImpl implements LocationController {
    @Autowired
    private LocationService locationService;

    @Override
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @Override
    public Optional<Location> findById(Integer id) {
        return locationService.findById(id);
    }

    @Override
    public int create(Location location) {
        return locationService.create(location);
    }

    @Override
    public int update(Integer id, Location location) {
        return locationService.update(id, location);
    }

    @Override
    public int delete(Integer id) {
        return locationService.delete(id);
    }
}
