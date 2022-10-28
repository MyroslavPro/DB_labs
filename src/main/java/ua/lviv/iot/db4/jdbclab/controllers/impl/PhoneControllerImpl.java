package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.controllers.PhoneController;
import ua.lviv.iot.db4.jdbclab.models.Phone;
import ua.lviv.iot.db4.jdbclab.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PhoneControllerImpl implements PhoneController {
    @Autowired
    private PhoneService phoneService;

    @Override
    public List<Phone> findAll() {
        return phoneService.findAll();
    }

    @Override
    public Optional<Phone> findById(Integer id) {
        return phoneService.findById(id);
    }

    @Override
    public int create(Phone phone) {
        return phoneService.create(phone);
    }

    @Override
    public int update(Integer id, Phone phone) {
        return phoneService.update(id, phone);
    }

    @Override
    public int delete(Integer id) {
        return phoneService.delete(id);
    }
}
