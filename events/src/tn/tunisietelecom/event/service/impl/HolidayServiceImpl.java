package tn.tunisietelecom.event.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.HolidayDao;

import tn.tunisietelecom.event.entities.Holiday;
import tn.tunisietelecom.event.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayDao holidayDao;

	@Transactional
	public void addHoliday(Holiday holiday) {
		this.holidayDao.save(holiday);

	}

}
