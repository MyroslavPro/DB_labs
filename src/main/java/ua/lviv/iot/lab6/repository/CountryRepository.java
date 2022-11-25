package ua.lviv.iot.lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.lab6.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	@Procedure("addNewCountry")
	String addNewCountry(String name);
	
	@Procedure("addTenCountries")
	void addTenCountries();
	
	@Procedure("generateTablesFromCountriesWithCursor")
	void generateTablesFromCountriesWithCursor();
}
