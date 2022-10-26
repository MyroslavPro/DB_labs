package ua.lviv.iot.db4.jdbclab.services;

import ua.lviv.iot.db4.jdbclab.models.Client;

import java.util.Optional;

public interface ClientService extends GeneralService<Client, Integer> {
	Optional<Client> findByClientName(String clientName);
}
