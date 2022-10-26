package ua.lviv.iot.db4.jdbclab.services.impl;

import ua.lviv.iot.db4.jdbclab.dao.LocationDao;
import ua.lviv.iot.db4.jdbclab.models.Location;
import ua.lviv.iot.db4.jdbclab.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationDao locationDao;

    @Override
    public List<Location> findAll() {
        return locationDao.findAll();
    }

    @Override
    public Optional<Location> findById(Integer id) {
        return locationDao.findById(id);
    }

    @Override
    public int create(Location location) {
        return locationDao.create(location);
    }

    @Override
    public int update(Integer id, Location location) {
        return locationDao.update(id, location);
    }

    @Override
    public int delete(Integer id) {
        return locationDao.delete(id);
    }
}
