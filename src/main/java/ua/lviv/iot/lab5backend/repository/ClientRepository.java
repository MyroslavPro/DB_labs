package ua.lviv.iot.lab5backend.repository;

import ua.lviv.iot.lab5backend.domain.Client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	List<Client> findClientsByCountryId(Integer countryId);
	List<Client> findClientsByLanguageId(Integer languageId);
	Client findClientByPhoneId(Integer phoneId);
}
