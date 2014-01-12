package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.LocalDao;
import tn.tunisietelecom.event.entities.Local;

@Repository
public class LocalDaoImpl implements LocalDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Local local) {
		em.persist(local);
	}

	public List<Local> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Local> criteria = builder.createQuery(Local.class);
		Root<Local> localRoot = criteria.from(Local.class);
		criteria.select(localRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<Local> findLocalsByPage(int firstResult, int maxResult) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Local> criteria = builder.createQuery(Local.class);
		Root<Local> localRoot = criteria.from(Local.class);
		criteria.select(localRoot);

		TypedQuery<Local> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);
		return typedQuery.getResultList();
	}

	public long countLocals() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Local> localRoot = criteria.from(Local.class);
		criteria.select(builder.count(localRoot));

		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public Local findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Local> criteria = builder.createQuery(Local.class);
		Root<Local> localRoot = criteria.from(Local.class);
		criteria.select(localRoot);
		criteria.where(builder.equal(localRoot.get("idLocal"), id));
		List<Local> locals = em.createQuery(criteria).getResultList();
		return locals.size() > 0 ? locals.get(0) : null;
	}

	public void delete(Local local) {
		em.remove(local);
	}

	public void update(Local local) {
		em.merge(local);
	}
}
