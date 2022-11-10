package ua.lviv.iot.lab5backend.service;

import ua.lviv.iot.lab5backend.domain.Phone;

public interface PhoneService extends GeneralService<Phone, Integer> {
	Phone findPhoneByClientId(Integer clientId);
	Phone findPhoneByGuideId(Integer guideId);
}
