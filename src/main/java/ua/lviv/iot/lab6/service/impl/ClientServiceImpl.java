package ua.lviv.iot.lab6.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab6.domain.Client;
import ua.lviv.iot.lab6.exception.ClientNotFoundException;
import ua.lviv.iot.lab6.exception.CountryNotFoundException;
import ua.lviv.iot.lab6.exception.LanguageNotFoundException;
import ua.lviv.iot.lab6.exception.PhoneNotFoundException;
import ua.lviv.iot.lab6.exception.TripNotFoundException;
import ua.lviv.iot.lab6.repository.ClientRepository;
import ua.lviv.iot.lab6.repository.CountryRepository;
import ua.lviv.iot.lab6.repository.LanguageRepository;
import ua.lviv.iot.lab6.repository.PhoneRepository;
import ua.lviv.iot.lab6.repository.TripRepository;
import ua.lviv.iot.lab6.service.ClientService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private PhoneRepository phoneRepository;
    
    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private LanguageRepository languageRepository;
    
    
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer id) {
        return clientRepository.findById(id)
        		.orElseThrow(() -> new ClientNotFoundException(id));
    }
    
    @Override
    public Client findClientByPhoneId(Integer phoneId) {
    	phoneRepository.findById(phoneId)
    		.orElseThrow(() -> new PhoneNotFoundException(phoneId));
    	return clientRepository.findClientByPhoneId(phoneId);
    }
    
    @Override
    public List<Client> findClientsByCountryId(Integer countryId){
    	countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
    	return clientRepository.findClientsByCountryId(countryId);
    }
    
    @Override
    public List<Client> findClientsByLanguageId(Integer languageId){
    	languageRepository.findById(languageId).orElseThrow(() -> new LanguageNotFoundException(languageId));
    	return clientRepository.findClientsByLanguageId(languageId);
    }

    @Override
    @Transactional
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void update(Integer id, Client updatedClient) {
    	Client client= clientRepository.findById(id)
              .orElseThrow(() -> new ClientNotFoundException(id));
        
    	client.setName(updatedClient.getName());
    	clientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Client client= clientRepository.findById(id)
                 .orElseThrow(() -> new ClientNotFoundException(id));
    	 
    	clientRepository.delete(client);
    }
    
    @Override
    public void fillManyToManyForClientsAndTrips(Integer client,Integer trip) {
    	clientRepository.fillManyToManyForClientsAndTrips(client,trip);
    }
}
