package ua.lviv.iot.lab5backend.repository;

import ua.lviv.iot.lab5backend.domain.Guide;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Integer> {
	List<Guide> findGuidesByCountryId(Integer countryId);
	Guide findGuideByPhoneId(Integer phoneId);
}
