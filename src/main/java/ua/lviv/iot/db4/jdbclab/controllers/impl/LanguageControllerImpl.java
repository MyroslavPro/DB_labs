package ua.lviv.iot.db4.jdbclab.controllers.impl;

import ua.lviv.iot.db4.jdbclab.controllers.LanguageController;
import ua.lviv.iot.db4.jdbclab.models.Language;
import ua.lviv.iot.db4.jdbclab.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageControllerImpl implements LanguageController{
    @Autowired
    private LanguageService languageService;

    @Override
    public List<Language> findAll() {
        return languageService.findAll();
    }

    @Override
    public Optional<Language> findById(Integer id) {
        return languageService.findById(id);
    }

    @Override
    public int create(Language language) {
        return languageService.create(language);
    }

    @Override
    public int update(Integer id, Language language) {
        return languageService.update(id, language);
    }

    @Override
    public int delete(Integer id) {
        return languageService.delete(id);
    }

    @Override
    public Optional<Language> findByLanguageName(String languageName) {
        return languageService.findByLanguageName(languageName);
    }
}
