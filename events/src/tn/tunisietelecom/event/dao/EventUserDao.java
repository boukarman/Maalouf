package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.EventUserId;
import tn.tunisietelecom.event.entities.User;

public interface EventUserDao {

	void save(EventUser eventUser);

	List<EventUser> findAll();

	void update(EventUser eventUser);

	List<EventUser> findByEvent(Event event);

	EventUser findById(EventUserId id);

	List<EventUser> findByUserWithPage(User user, int firstResult, int maxResult);

	long countEventUserWithUser(User user);

}
