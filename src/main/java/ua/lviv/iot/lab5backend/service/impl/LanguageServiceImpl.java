package ua.lviv.iot.lab5backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.lab5backend.domain.Language;
import ua.lviv.iot.lab5backend.exception.LanguageNotFoundException;

import ua.lviv.iot.lab5backend.repository.LanguageRepository;
import ua.lviv.iot.lab5backend.service.LanguageService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language findById(Integer id) {
        return languageRepository.findById(id)
        		.orElseThrow(() -> new LanguageNotFoundException(id));
    }

    @Override
    @Transactional
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    @Transactional
    public void update(Integer id, Language updatedLanguage) {
    	Language language = languageRepository.findById(id)
              .orElseThrow(() -> new LanguageNotFoundException(id));
        
    	language.setName(updatedLanguage.getName());
        languageRepository.save(language);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    	Language language = languageRepository.findById(id)
                 .orElseThrow(() -> new LanguageNotFoundException(id));
    	 
    	 languageRepository.delete(language);
    }
}
