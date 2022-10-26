package ua.lviv.iot.db4.jdbclab.dao;

import ua.lviv.iot.db4.jdbclab.models.Language;

import java.util.Optional;

public interface LanguageDao extends GeneralDao<Language, Integer> {
    Optional<Language> findByLanguageName(String languageName);
}
