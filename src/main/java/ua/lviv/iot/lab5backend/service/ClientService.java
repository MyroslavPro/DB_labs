package ua.lviv.iot.lab5backend.service;

import java.util.List;

import ua.lviv.iot.lab5backend.domain.Client;

public interface ClientService extends GeneralService<Client, Integer> {
	List<Client> findClientsByCountryId(Integer countryId);
	List<Client> findClientsByLanguageId(Integer languageId);
	Client findClientByPhoneId(Integer phoneId);
}
