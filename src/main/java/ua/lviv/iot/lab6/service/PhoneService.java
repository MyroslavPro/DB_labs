package ua.lviv.iot.lab6.service;

import ua.lviv.iot.lab6.domain.Phone;

public interface PhoneService extends GeneralService<Phone, Integer> {
	Phone findPhoneByClientId(Integer clientId);
	Phone findPhoneByGuideId(Integer guideId);
}
