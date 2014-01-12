package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.EventUserDao;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.EventUserId;
import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.service.EventUserService;

@Service
public class EventUserServiceImpl implements EventUserService {

	@Autowired
	private EventUserDao eventUserDao;

	@Transactional
	public void save(EventUser eventUser) {
		eventUserDao.save(eventUser);
	}

	public List<EventUser> findAll() {
		return eventUserDao.findAll();
	}

	public EventUser findById(EventUserId id) {
		return eventUserDao.findById(id);
	}

	public List<EventUser> findByUserWithPage(User user, int firstResult,
			int maxResult) {
		return eventUserDao.findByUserWithPage(user, firstResult, maxResult);
	}

	public long countEventUserWithUser(User user) {
		return eventUserDao.countEventUserWithUser(user);
	}

}
