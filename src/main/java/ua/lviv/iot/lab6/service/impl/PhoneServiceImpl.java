package ua.lviv.iot.lab6.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab6.domain.Phone;
import ua.lviv.iot.lab6.exception.ClientNotFoundException;
import ua.lviv.iot.lab6.exception.GuideNotFoundException;
import ua.lviv.iot.lab6.exception.PhoneNotFoundException;
import ua.lviv.iot.lab6.repository.ClientRepository;
import ua.lviv.iot.lab6.repository.GuideRepository;
import ua.lviv.iot.lab6.repository.PhoneRepository;
import ua.lviv.iot.lab6.service.PhoneService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private GuideRepository guideRepository;

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone findById(Integer id) {
        return phoneRepository.findById(id)
        		.orElseThrow(() -> new PhoneNotFoundException(id));
    }
    
    @Override
    public Phone findPhoneByClientId(Integer clientId) {
    	clientRepository.findById(clientId)
    		.orElseThrow(() -> new ClientNotFoundException(clientId));
    	return phoneRepository.findPhoneByClientId(clientId);
    }
    
    @Override
    public Phone findPhoneByGuideId(Integer guideId) {
    	guideRepository.findById(guideId)
    			.orElseThrow(() -> new GuideNotFoundException(guideId));
    	return phoneRepository.findPhoneByGuideId(guideId);
    }

    @Override
    @Transactional
    public Phone create(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    @Transactional
    public void update(Integer id, Phone updatedPhone) {
    	Phone phone= phoneRepository.findById(id)
              .orElseThrow(() -> new PhoneNotFoundException(id));
        
    	phone.setNumber(updatedPhone.getNumber());
        phoneRepository.save(phone);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Phone language = phoneRepository.findById(id)
                 .orElseThrow(() -> new PhoneNotFoundException(id));
    	 
    	 phoneRepository.delete(language);
    }
}
