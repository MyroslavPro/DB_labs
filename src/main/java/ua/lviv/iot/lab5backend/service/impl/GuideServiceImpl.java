package ua.lviv.iot.lab5backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab5backend.domain.Guide;
import ua.lviv.iot.lab5backend.exception.PhoneNotFoundException;
import ua.lviv.iot.lab5backend.exception.GuideNotFoundException;
import ua.lviv.iot.lab5backend.exception.CountryNotFoundException;
import ua.lviv.iot.lab5backend.repository.CountryRepository;
import ua.lviv.iot.lab5backend.repository.GuideRepository;
import ua.lviv.iot.lab5backend.repository.PhoneRepository;
import ua.lviv.iot.lab5backend.service.GuideService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class GuideServiceImpl implements GuideService {
	@Autowired
    private GuideRepository guideRepository;
	
	@Autowired
    private PhoneRepository phoneRepository;
    
    @Autowired
    private CountryRepository countryRepository;
    
    

    @Override
    public List<Guide> findAll() {
        return guideRepository.findAll();
    }

    @Override
    public Guide findById(Integer id) {
        return guideRepository.findById(id)
        		.orElseThrow(() -> new GuideNotFoundException(id));
    }
    
    @Override
    public Guide findGuideByPhoneId(Integer phoneId) {
    	phoneRepository.findById(phoneId)
    		.orElseThrow(() -> new PhoneNotFoundException(phoneId));
    	return guideRepository.findGuideByPhoneId(phoneId);
    }
    
    @Override
    public List<Guide> findGuidesByCountryId(Integer countryId){
    	countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
    	return guideRepository.findGuidesByCountryId(countryId);
    }

    @Override
    @Transactional
    public Guide create(Guide guide) {
        return guideRepository.save(guide);
    }

    @Override
    @Transactional
    public void update(Integer id, Guide updatedGuide) {
    	Guide guide= guideRepository.findById(id)
              .orElseThrow(() -> new PhoneNotFoundException(id));
        
    	guide.setName(updatedGuide.getName());
    	guide.setSurname(updatedGuide.getSurname());
    	guide.setEmail(updatedGuide.getEmail());
    	guideRepository.save(guide);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Guide guide= guideRepository.findById(id)
                 .orElseThrow(() -> new GuideNotFoundException(id));
    	 
    	guideRepository.delete(guide);
    }
}
