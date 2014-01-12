package tn.tunisietelecom.event.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.InterferenceDao;

import tn.tunisietelecom.event.entities.Interference;
import tn.tunisietelecom.event.service.InterferenceService;

@Service
public class InterferenceServiceImpl implements InterferenceService {

	@Autowired
	private InterferenceDao interfereDao;

	@Transactional
	public void addInterfernce(Interference interference) {
		this.interfereDao.save(interference);

	}

}
