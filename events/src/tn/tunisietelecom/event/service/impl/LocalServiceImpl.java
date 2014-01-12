package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.LocalDao;
import tn.tunisietelecom.event.entities.Local;
import tn.tunisietelecom.event.service.LocalService;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	private LocalDao localDao;

	@Transactional
	public void addLocal(Local local) {
		this.localDao.save(local);
	}

	public List<Local> findAllLocals() {
		return this.localDao.findAll();
	}

	public Local findById(long id) {
		return this.localDao.findById(id);
	}

	@Transactional
	public void deleteLocal(Local local) {
		localDao.delete(local);
	}

	@Transactional
	public void update(Local local) {
		localDao.update(local);
	}

	public List<Local> findLocalsByPage(int firstResult, int maxResult) {
		return localDao.findLocalsByPage(firstResult, maxResult);
	}

	public long countLocals() {
		return localDao.countLocals();
	}

}
