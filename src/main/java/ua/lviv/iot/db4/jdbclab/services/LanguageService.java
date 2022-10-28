package ua.lviv.iot.db4.jdbclab.services;

import ua.lviv.iot.db4.jdbclab.models.Language;

import java.util.Optional;

public interface LanguageService extends GeneralService<Language, Integer> {
	Optional<Language> findByLanguageName(String languageName);
}
