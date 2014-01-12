package tn.tunisietelecom.event.dao;

import java.util.Date;
import java.util.List;

import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.User;

public interface EventDao {
	
	Event save(Event event);
	
	Event findById(long id);
	
	Event find(Event event);
	
	List<Event> findEventsByPage(int firstResult, int maxResult);
	
	List<Event> findAll();
	
	void delete(long idEvent);
	
	long countEventNumberWithCause(long idCause);
	
	long countEventNumberWithCause(long idCause, Long localId, Date startDate,
			Date endDate);
	
	long countEventNumberWithAgents(User agent, Date startDate, Date endDate);
	
	void update(Event event);
	
	List<EventUser> retrieveFinishedEvents();
	
	long findCurrentEventStatus(Event event);
	
}
