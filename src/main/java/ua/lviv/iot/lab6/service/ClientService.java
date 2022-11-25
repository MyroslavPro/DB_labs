package ua.lviv.iot.lab6.service;

import java.util.List;

import ua.lviv.iot.lab6.domain.Client;

public interface ClientService extends GeneralService<Client, Integer> {
	List<Client> findClientsByCountryId(Integer countryId);
	List<Client> findClientsByLanguageId(Integer languageId);
	Client findClientByPhoneId(Integer phoneId);
	void fillManyToManyForClientsAndTrips(Integer client,Integer trip);
}
