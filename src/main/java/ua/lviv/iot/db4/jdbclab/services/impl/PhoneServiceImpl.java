package ua.lviv.iot.db4.jdbclab.services.impl;

import ua.lviv.iot.db4.jdbclab.dao.PhoneDao;
import ua.lviv.iot.db4.jdbclab.models.Phone;
import ua.lviv.iot.db4.jdbclab.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneDao phoneDao;

    @Override
    public List<Phone> findAll() {
        return phoneDao.findAll();
    }

    @Override
    public Optional<Phone> findById(Integer id) {
        return phoneDao.findById(id);
    }

    @Override
    public int create(Phone phone) {
        return phoneDao.create(phone);
    }

    @Override
    public int update(Integer id, Phone phone) {
        return phoneDao.update(id, phone);
    }

    @Override
    public int delete(Integer id) {
        return phoneDao.delete(id);
    }
}
