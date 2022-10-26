package ua.lviv.iot.db4.jdbclab.services.impl;

import ua.lviv.iot.db4.jdbclab.dao.LanguageDao;
import ua.lviv.iot.db4.jdbclab.models.Language;
import ua.lviv.iot.db4.jdbclab.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageDao languageDao;

    @Override
    public List<Language> findAll() {
        return languageDao.findAll();
    }

    @Override
    public Optional<Language> findById(Integer id) {
        return languageDao.findById(id);
    }

    @Override
    public int create(Language language) {
        return languageDao.create(language);
    }

    @Override
    public int update(Integer id, Language language) {
        return languageDao.update(id, language);
    }

    @Override
    public int delete(Integer id) {
        return languageDao.delete(id);
    }

    @Override
    public Optional<Language> findByLanguageName(String languageName) {
        return languageDao.findByLanguageName(languageName);
    }
}
