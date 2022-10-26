package ua.lviv.iot.db4.jdbclab.controllers;

import ua.lviv.iot.db4.jdbclab.models.Language;

import java.util.Optional;

public interface LanguageController extends GeneralController<Language, Integer> {
	Optional<Language> findByLanguageName(String languageName);
}
