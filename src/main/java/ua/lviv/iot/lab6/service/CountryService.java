package ua.lviv.iot.lab6.service;

import ua.lviv.iot.lab6.domain.Country;

public interface CountryService extends GeneralService<Country, Integer> {
	void addTenCountries();
	void generateTablesFromCountriesWithCursor();
}
