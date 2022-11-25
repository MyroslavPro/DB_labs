package ua.lviv.iot.lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.lab6.domain.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
	 Phone findPhoneByClientId(Integer clientId);
	 Phone findPhoneByGuideId(Integer guideId);
}
