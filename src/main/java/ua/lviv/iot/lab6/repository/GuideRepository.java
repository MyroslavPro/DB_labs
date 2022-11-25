package ua.lviv.iot.lab6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.lab6.domain.Guide;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Integer> {
	List<Guide> findGuidesByCountryId(Integer countryId);
	Guide findGuideByPhoneId(Integer phoneId);
}
