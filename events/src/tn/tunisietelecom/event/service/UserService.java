package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.Role;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.entities.User;

public interface UserService {

	User addUser(User user);

	void update(User user);

	List<User> findByLogin(String login);

	List<User> findAllUsers();

	List<User> findAllResponsibles();

	List<User> findEESAgentAndSubcontrator();

	User findById(long id);

	List<SubcontractorType> findAllSubcontractorType();

	List<Role> findAllRoles();

	List<User> findUsersByPage(int firstResult, int maxResult);

	long countUsers();

	void deleteUser(User user);

}
