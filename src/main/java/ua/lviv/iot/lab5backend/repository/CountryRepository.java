package ua.lviv.iot.lab5backend.repository;

import ua.lviv.iot.lab5backend.domain.Country;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
