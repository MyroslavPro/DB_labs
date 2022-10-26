package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.services.ClientService;
import ua.lviv.iot.db4.jdbclab.models.Client;
import ua.lviv.iot.db4.jdbclab.controllers.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientControllerImpl implements ClientController {
    @Autowired
    private ClientService clientService;

    @Override
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public int create(Client client) {
        return clientService.create(client);
    }

    @Override
    public int update(Integer id, Client client) {
        return clientService.update(id, client);
    }

    @Override
    public int delete(Integer id) {
        return clientService.delete(id);
    }

    @Override
    public Optional<Client> findByClientName(String clientName) {
        return clientService.findByClientName(clientName);
    }
}
