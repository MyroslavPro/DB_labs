package ua.lviv.iot.lab5backend.repository;

import ua.lviv.iot.lab5backend.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
	 Phone findPhoneByClientId(Integer clientId);
	 Phone findPhoneByGuideId(Integer guideId);
}
