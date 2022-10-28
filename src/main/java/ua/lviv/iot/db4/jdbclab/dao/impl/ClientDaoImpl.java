package ua.lviv.iot.db4.jdbclab.dao.impl;

import ua.lviv.iot.db4.jdbclab.dao.ClientDao;
import ua.lviv.iot.db4.jdbclab.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDaoImpl implements ClientDao {
    private static final String FIND_ALL = "SELECT * FROM client";
    private static final String CREATE = "INSERT client(name, country_id, language_id, phone_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE client SET name=?, country_id=?, language_id=?, phone_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM client WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM client WHERE id=?";
    private static final String FIND_BY_CLIENT_NAME = "SELECT * FROM client WHERE name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client;
        try {
        	client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Client.class), id));
        } catch (EmptyResultDataAccessException e) {
        	client = Optional.empty();
        }
        return client;
    }

    @Override
    public int create(Client client) {
        return jdbcTemplate.update(CREATE, client.getName(), client.getCountryId(), client.getLanguageId(), client.getPhoneId());
    }

    @Override
    public int update(Integer id, Client client) {
        return jdbcTemplate.update(UPDATE, client.getName(), client.getCountryId(), client.getLanguageId(), client.getPhoneId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Client> findByClientName(String clientName) {
        Optional<Client> client;
        try {
        	client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CLIENT_NAME,
                    BeanPropertyRowMapper.newInstance(Client.class), clientName));
        } catch (EmptyResultDataAccessException e) {
        	client = Optional.empty();
        }
        return client;
    }

}
