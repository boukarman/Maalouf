package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.Role;

public interface RoleService {

	Role findById(long id);

	List<Role> findAll();

}
