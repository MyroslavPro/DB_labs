package ua.lviv.iot.db4.jdbclab.controllers;

import ua.lviv.iot.db4.jdbclab.models.Client;

import java.util.Optional;

public interface ClientController extends GeneralController<Client, Integer> {
	Optional<Client> findByClientName(String clientName);
}
