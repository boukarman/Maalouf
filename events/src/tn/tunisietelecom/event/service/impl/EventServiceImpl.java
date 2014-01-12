package tn.tunisietelecom.event.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.EventDao;
import tn.tunisietelecom.event.dao.EventUserDao;
import tn.tunisietelecom.event.dao.UserDao;
import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.EventUserId;
import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.service.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EventUserDao eventUserDao;
	
	@Transactional
	public Event addEvent(Event event) {
		return this.eventDao.save(event);
	}
	
	@Transactional
	public void deleteEvent(long idEvent) {
		this.eventDao.delete(idEvent);
	}
	
	public List<Event> findAllEvents() {
		return this.eventDao.findAll();
	}
	
	public long countEventNumberWithCause(long idCause) {
		return this.eventDao.countEventNumberWithCause(idCause);
	}
	
	public long countEventNumberWithCause(long idCause, Long localId,
			Date startDate, Date endDate) {
		return this.eventDao.countEventNumberWithCause(idCause, localId,
				startDate, endDate);
	}
	
	@Transactional
	public void updateStatus(long idEvent, Integer status, long userId) {
		User user = userDao.findById(userId);
		Event event = eventDao.findById(idEvent);
		EventUserId statusId = new EventUserId(user, event);
		EventUser eventUser = eventUserDao.findById(statusId);
		if (eventUser == null) {
			eventUser = new EventUser();
			eventUser.setId(statusId);
		}
		eventUser.setStatus(status);
		switch (status) {
		case 0:
			eventUser.setRecievedDate(new Date());
			break;
		case 1:
			if (event.getNotifiedUsers() == null)
				event.setNotifiedUsers(new ArrayList<User>());
			eventUser.setReadDate(new Date());
			break;
		case 2:
			Date startDate = new Date();
			eventUser.setStartDate(startDate);
			/*
			 * for (EventUser eventUserOther : eventUserDao.findByEvent(event)) {
			 * eventUserOther.setStartDate(startDate);
			 * eventUserOther.setStatus(status);
			 * eventUserDao.update(eventUserOther);
			 * }
			 */
			event.setWorkingInUser(user);
			break;
		case 3:
			Date finishedDate = new Date();
			eventUser.setFinishedDate(finishedDate);
			/*
			 * for (EventUser eventUserOther : eventUserDao.findByEvent(event)) {
			 * eventUserOther.setFinishedDate(finishedDate);
			 * eventUserOther.setStatus(status);
			 * eventUserDao.update(eventUserOther);
			 * }
			 */
			break;
		}
		if (!event.getNotifiedUsers().contains(user))
			event.getNotifiedUsers().add(user);
		eventUserDao.update(eventUser);
		eventDao.update(event);
	}
	
	public List<User> findEventNotifiedUsers(long idEvent) {
		Event event = eventDao.findById(idEvent);
		return event.getNotifiedUsers();
	}
	
	public User findEventWorkingInUser(long idEvent) {
		Event event = eventDao.findById(idEvent);
		return event.getWorkingInUser();
	}
	
	@Transactional
	public void update(Event event) {
		eventDao.update(event);
	}
	
	public Event find(Event event) {
		return eventDao.find(event);
	}
	
	public Event findById(Long idEvent) {
		return eventDao.findById(idEvent);
	}
	
	public List<Event> findEventsByPage(int firstResult, int maxResult) {
		return eventDao.findEventsByPage(firstResult, maxResult);
	}
	
	public long countEventNumberWithAgents(User agent, Date startDate,
			Date endDate) {
		return eventDao.countEventNumberWithAgents(agent, startDate, endDate);
	}
	
	public Map<String, Object> retrieveChartPerformance() {
		List<EventUser> eventUserList = eventDao.retrieveFinishedEvents();
		Map<String, Integer> results = new HashMap<String, Integer>();
		Map<String, List<Long>> relatedEvents = new HashMap<String, List<Long>>();
		Map<String, Object> chartElements = new HashMap<String, Object>();
		for (EventUser eventUser : eventUserList) {
			Calendar c1 = DateUtils.toCalendar(eventUser.getFinishedDate());
			// between 0 and 2 hours
			Date finishedDate = DateUtils.addHours(eventUser.getStartDate(), 2);
			Calendar c2 = DateUtils.toCalendar(finishedDate);
			if (c1.compareTo(c2) <= 0) {
				Integer val = results.get("0-2:heures") == null ? 1
						: results.get("0-2:heures") + 1;
				results.put("0-2:heures", val);
				Long idEvent = eventUser.getId().getEvent().getIdEvent();
				if (relatedEvents.get("0-2:heures") == null) {
					relatedEvents.put("0-2:heures", new ArrayList<Long>());
				}
				relatedEvents.get("0-2:heures").add(idEvent);
			}
			// between 2 and 4 hours
			Date finishedDate2 = DateUtils
					.addHours(eventUser.getStartDate(), 4);
			Calendar c3 = DateUtils.toCalendar(finishedDate2);
			if (c1.compareTo(c2) > 0 && c1.compareTo(c3) <= 0) {
				Integer val = results.get("2-4:heures") == null ? 1
						: results.get("2-4:heures") + 1;
				results.put("2-4:heures", val);
				Long idEvent = eventUser.getId().getEvent().getIdEvent();
				if (relatedEvents.get("2-4:heures") == null) {
					relatedEvents.put("2-4:heures", new ArrayList<Long>());
				}
				relatedEvents.get("2-4:heures").add(idEvent);
			}
			// greater than 4 hours
			if (c1.compareTo(c3) > 0) {
				Integer val = results.get(">4:heures") == null ? 1
						: results.get(">4:heures") + 1;
				results.put(">4:heures", val);
				Long idEvent = eventUser.getId().getEvent().getIdEvent();
				if (relatedEvents.get(">4:heures") == null) {
					relatedEvents.put(">4:heures", new ArrayList<Long>());
				}
				relatedEvents.get(">4:heures").add(idEvent);
			}
		}
		chartElements.put("chartCriteria", results);
		chartElements.put("relatedEvents", relatedEvents);
		return chartElements;
	}
	
	public long findCurrentEventStatus(Event event) {
		return eventDao.findCurrentEventStatus(event);
	}
}
