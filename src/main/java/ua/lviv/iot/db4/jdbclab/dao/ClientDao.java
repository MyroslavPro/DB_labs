package ua.lviv.iot.db4.jdbclab.dao;

import ua.lviv.iot.db4.jdbclab.models.Client;

import java.util.Optional;

public interface ClientDao extends GeneralDao<Client, Integer> {
    Optional<Client> findByClientName(String clientName);
}
