package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.CauseDao;
import tn.tunisietelecom.event.entities.Cause;

@Repository
public class CauseDaoImpl implements CauseDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Cause cause) {
		em.persist(cause);
	}

	public List<Cause> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cause> criteria = builder.createQuery(Cause.class);
		Root<Cause> causeRoot = criteria.from(Cause.class);
		criteria.select(causeRoot);

		return em.createQuery(criteria).getResultList();
	}

	public Cause findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cause> criteria = builder.createQuery(Cause.class);
		Root<Cause> causeRoot = criteria.from(Cause.class);
		criteria.select(causeRoot);
		criteria.where(builder.equal(causeRoot.get("causeId"), id));
		List<Cause> causes = em.createQuery(criteria).getResultList();
		return causes.size() > 0 ? causes.get(0) : null;
	}
}
