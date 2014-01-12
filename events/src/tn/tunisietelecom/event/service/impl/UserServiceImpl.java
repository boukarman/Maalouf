package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.UserDao;
import tn.tunisietelecom.event.entities.Role;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public User addUser(User user) {
		return this.userDao.save(user);
	}

	public List<User> findByLogin(String login) {
		return this.userDao.findByLogin(login);
	}

	public List<User> findAllUsers() {
		return this.userDao.findAll();
	}

	public List<User> findAllResponsibles() {
		return userDao.findEESAgent();
	}

	public User findById(long id) {
		return userDao.findById(id);
	}

	public List<User> findEESAgentAndSubcontrator() {
		return userDao.findEESAgentAndSubcontrator();
	}

	public List<SubcontractorType> findAllSubcontractorType() {
		return userDao.findAllSubcontractorType();
	}

	public List<Role> findAllRoles() {
		return userDao.findAllRoles();
	}

	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	public List<User> findUsersByPage(int firstResult, int maxResult) {
		return userDao.findUsersByPage(firstResult, maxResult);
	}

	public long countUsers() {
		return userDao.countUsers();
	}

	@Transactional
	public void deleteUser(User user) {
		userDao.delete(user);
	}

}
