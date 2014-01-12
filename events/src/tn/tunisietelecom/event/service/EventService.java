package tn.tunisietelecom.event.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.User;

public interface EventService {
	
	Event addEvent(Event event);
	
	List<Event> findAllEvents();
	
	void deleteEvent(long idEvent);
	
	long countEventNumberWithCause(long idCause);
	
	long countEventNumberWithCause(long idCause, Long localId, Date startDate,
			Date endDate);
	
	long countEventNumberWithAgents(User agent, Date startDate, Date endDate);
	
	void updateStatus(long idEvent, Integer status, long userId);
	
	List<User> findEventNotifiedUsers(long idEvent);
	
	User findEventWorkingInUser(long idEvent);
	
	void update(Event event);
	
	Event find(Event event);
	
	List<Event> findEventsByPage(int firstResult, int maxResult);
	
	Event findById(Long idEvent);
	
	Map<String, Object> retrieveChartPerformance();
	
	long findCurrentEventStatus(Event event);
}
