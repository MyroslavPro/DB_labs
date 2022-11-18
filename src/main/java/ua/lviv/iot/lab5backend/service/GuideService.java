package ua.lviv.iot.lab5backend.service;

import java.util.List;

import ua.lviv.iot.lab5backend.domain.Guide;

public interface GuideService extends GeneralService<Guide, Integer> {
	List<Guide> findGuidesByCountryId(Integer countryId);
	Guide findGuideByPhoneId(Integer phoneId);
}
