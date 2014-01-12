package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.tunisietelecom.event.dao.SubcontractorTypeDao;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.service.SubcontractorTypeService;

@Service
public class SubcontractorTypeServiceImpl implements SubcontractorTypeService {

	@Autowired
	private SubcontractorTypeDao subcontractorTypeDao;

	public List<SubcontractorType> findAll() {
		return subcontractorTypeDao.findAll();
	}

	public SubcontractorType findById(long id) {
		return subcontractorTypeDao.findById(id);
	}

}
