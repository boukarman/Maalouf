package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.Role;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.entities.User;

public interface UserDao {

	List<User> findByLogin(String login);

	List<User> findAll();

	User save(User user);

	void update(User user);

	List<User> findEESAgent();

	List<User> findEESAgentAndSubcontrator();

	User findById(long id);

	List<SubcontractorType> findAllSubcontractorType();

	List<Role> findAllRoles();

	List<User> findUsersByPage(int firstResult, int maxResult);

	long countUsers();

	void delete(User user);

}
