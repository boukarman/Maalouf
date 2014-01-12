package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.EventUserDao;
import tn.tunisietelecom.event.entities.Event;
import tn.tunisietelecom.event.entities.EventUser;
import tn.tunisietelecom.event.entities.EventUserId;
import tn.tunisietelecom.event.entities.User;

@Repository
public class EventUserDaoImpl implements EventUserDao {

	@PersistenceContext
	private EntityManager em;

	public void save(EventUser eventUser) {
		em.persist(eventUser);
	}

	public void update(EventUser eventUser) {
		em.merge(eventUser);
	}

	public List<EventUser> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<EventUser> criteria = builder
				.createQuery(EventUser.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(eventUserRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<EventUser> findByUserWithPage(User user, int firstResult,
			int maxResult) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<EventUser> criteria = builder
				.createQuery(EventUser.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(eventUserRoot);
		criteria.where(builder.equal(eventUserRoot.get("id").get("user"), user));
		TypedQuery<EventUser> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);
		return typedQuery.getResultList();
	}

	public long countEventUserWithUser(User user) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(builder.count(eventUserRoot));
		criteria.where(builder.equal(eventUserRoot.get("id").get("user"), user));
		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public List<EventUser> findByEvent(Event event) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<EventUser> criteria = builder
				.createQuery(EventUser.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(eventUserRoot);
		criteria.where(builder.equal(eventUserRoot.get("id").get("event"),
				event));
		return em.createQuery(criteria).getResultList();
	}

	public EventUser findById(EventUserId id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<EventUser> criteria = builder
				.createQuery(EventUser.class);
		Root<EventUser> eventUserRoot = criteria.from(EventUser.class);
		criteria.select(eventUserRoot);
		criteria.where(builder.equal(eventUserRoot.get("id"), id));
		List<EventUser> eventUsers = em.createQuery(criteria).getResultList();
		return eventUsers.size() > 0 ? eventUsers.get(0) : null;
	}

}
