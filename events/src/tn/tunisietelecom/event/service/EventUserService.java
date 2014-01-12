package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.EventUserId;
import tn.tunisietelecom.event.entities.User;

public interface EventUserService {

	void save(EventUser eventUser);

	List<EventUser> findAll();

	EventUser findById(EventUserId id);

	List<EventUser> findByUserWithPage(User user, int firstResult, int maxResult);

	long countEventUserWithUser(User user);
}
