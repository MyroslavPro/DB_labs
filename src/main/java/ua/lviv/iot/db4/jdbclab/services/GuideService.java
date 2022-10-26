package ua.lviv.iot.db4.jdbclab.services;

import ua.lviv.iot.db4.jdbclab.models.Guide;

import java.util.Optional;

public interface GuideService extends GeneralService<Guide, Integer> {
	Optional<Guide> findByGuideName(String guideName);
    
    Optional<Guide> findByGuideSurname(String guideName);
    
    Optional<Guide> findByGuideEmail(String clientName);
}
