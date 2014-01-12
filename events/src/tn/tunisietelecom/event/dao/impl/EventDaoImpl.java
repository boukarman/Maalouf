package tn.tunisietelecom.event.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.EventDao;
import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.User;

@Repository
public class EventDaoImpl implements EventDao {

	@PersistenceContext
	private EntityManager em;

	public Event save(Event event) {
		em.persist(event);
		em.flush();
		return event;
	}

	public Event find(Event event) {
		return em.find(Event.class, event);
	}

	public Event findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		Root<Event> eventRoot = criteria.from(Event.class);
		criteria.select(eventRoot);
		criteria.where(builder.equal(eventRoot.get("idEvent"), id));
		List<Event> events = em.createQuery(criteria).getResultList();
		return events.size() > 0 ? events.get(0) : null;
	}

	public long countEventNumberWithCause(long idCause) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Event> eventRoot = criteria.from(Event.class);
		criteria.select(builder.count(eventRoot));

		criteria.where(builder.equal(eventRoot.get("cause").get("causeId"),
				idCause));
		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public List<Event> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		Root<Event> eventRoot = criteria.from(Event.class);
		criteria.select(eventRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<Event> findEventsByPage(int firstResult, int maxResult) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		Root<Event> eventRoot = criteria.from(Event.class);
		criteria.select(eventRoot);
		TypedQuery<Event> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);
		return typedQuery.getResultList();
	}

	public void delete(long idEvent) {
		Event event = findById(idEvent);
		em.remove(event);
	}

	public long countEventNumberWithCause(long idCause, Long localId,
			Date startDate, Date endDate) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Event> eventRoot = criteria.from(Event.class);
		criteria.select(builder.count(eventRoot));

		Predicate p1 = builder.equal(eventRoot.get("cause").get("causeId"),
				idCause);
		Predicate p2 = builder.equal(eventRoot.get("piece").get("local"),
				localId);
		Predicate p3 = builder.between(eventRoot.<Date> get("dateEvent"),
				startDate, endDate);

		criteria.where(builder.and(p1, p2, p3));

		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public long countEventNumberWithAgents(User agent, Date startDate,
			Date endDate) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(builder.countDistinct(eventUserRoot.get("id").get(
				"event")));

		Predicate p1 = builder.equal(
				eventUserRoot.get("id").get("event").get("workingInUser"),
				agent);
		Predicate p2 = builder.ge(eventUserRoot.<Integer> get("status"), 2);
		Predicate p3 = builder.between(eventUserRoot.<Date> get("startDate"),
				startDate, endDate);

		criteria.where(builder.and(p1, p2, p3));

		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public long findCurrentEventStatus(Event event) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(builder.max(eventUserRoot.<Long> get("status")));
		criteria.where(builder.equal(eventUserRoot.get("id").get("event"),
				event));
		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public void update(Event event) {
		em.merge(event);
	}

	public List<EventUser> retrieveFinishedEvents() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<EventUser> criteria = builder
				.createQuery(EventUser.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(eventUserRoot).distinct(true);
		Predicate p1 = builder.equal(eventUserRoot.get("status"), 3);

		Predicate p2 = builder.equal(eventUserRoot.get("id").get("user"),
				eventUserRoot.get("id").get("event").get("workingInUser"));

		// Predicate p3 = builder.isNotNull(eventUserRoot.get("startDate"));
		criteria.where(builder.and(p1, p2));
		TypedQuery<EventUser> typedQuery = em.createQuery(criteria);
		return typedQuery.getResultList();
	}

}
