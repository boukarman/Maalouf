package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.CauseDao;
import tn.tunisietelecom.event.entities.Cause;
import tn.tunisietelecom.event.service.CauseService;

@Service
public class CauseServiceImpl implements CauseService {

	@Autowired
	private CauseDao causeDao;

	@Transactional
	public void addCause(Cause cause) {
		this.causeDao.save(cause);

	}

	public List<Cause> findAllCauses() {
		return this.causeDao.findAll();
	}

	public Cause findById(long id) {
		return this.causeDao.findById(id);
	}

}
