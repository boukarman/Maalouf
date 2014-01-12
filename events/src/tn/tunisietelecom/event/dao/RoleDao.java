package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.Role;

public interface RoleDao {

	Role findById(long id);

	List<Role> findAll();
}
