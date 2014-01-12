package tn.tunisietelecom.event.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.PrivilegeDao;
import tn.tunisietelecom.event.entities.Privilege;
import tn.tunisietelecom.event.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;

	@Transactional
	public void addPrivilege(Privilege privilege) {
		this.privilegeDao.save(privilege);

	}

}
