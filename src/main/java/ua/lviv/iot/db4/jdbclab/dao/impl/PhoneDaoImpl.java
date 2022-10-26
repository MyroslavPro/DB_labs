package ua.lviv.iot.db4.jdbclab.dao.impl;

import ua.lviv.iot.db4.jdbclab.dao.PhoneDao;
import ua.lviv.iot.db4.jdbclab.models.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PhoneDaoImpl implements PhoneDao {
    private static final String FIND_ALL = "SELECT * FROM phone";
    private static final String CREATE = "INSERT phone(name) VALUES (?)";
    private static final String UPDATE = "UPDATE phone SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM phone WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM phone WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Phone> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Phone.class));
    }

    @Override
    public Optional<Phone> findById(Integer id) {
        Optional<Phone> phone;
        try {
        	phone = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Phone.class), id));
        } catch (EmptyResultDataAccessException e) {
        	phone = Optional.empty();
        }
        return phone;
    }

    @Override
    public int create(Phone phone) {
        return jdbcTemplate.update(CREATE, phone.getName());
    }

    @Override
    public int update(Integer id, Phone phone) {
        return jdbcTemplate.update(UPDATE, phone.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
