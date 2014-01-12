package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.tunisietelecom.event.dao.RoleDao;
import tn.tunisietelecom.event.entities.Role;
import tn.tunisietelecom.event.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public Role findById(long id) {
		return roleDao.findById(id);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

}
