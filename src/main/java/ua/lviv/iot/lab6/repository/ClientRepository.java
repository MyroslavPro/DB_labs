package ua.lviv.iot.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.lab6.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	List<Client> findClientsByCountryId(Integer countryId);
	List<Client> findClientsByLanguageId(Integer languageId);
	// List<Client> findClientsByTripId(Integer tripId);
	Client findClientByPhoneId(Integer phoneId);
	
	@Procedure("fillManyToManyForClientsAndTrips")
	void fillManyToManyForClientsAndTrips(Integer client,Integer trip);
}
