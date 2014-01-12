package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.SubcontractorTypeDao;
import tn.tunisietelecom.event.entities.SubcontractorType;

@Repository
public class SubcontractorTypeDaoImpl implements SubcontractorTypeDao {

	@PersistenceContext
	private EntityManager em;

	public List<SubcontractorType> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<SubcontractorType> criteria = builder
				.createQuery(SubcontractorType.class);
		Root<SubcontractorType> subcontractorTypeRoot = criteria
				.from(SubcontractorType.class);
		criteria.select(subcontractorTypeRoot);

		return em.createQuery(criteria).getResultList();
	}

	public SubcontractorType findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<SubcontractorType> criteria = builder
				.createQuery(SubcontractorType.class);
		Root<SubcontractorType> root = criteria.from(SubcontractorType.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get("id"), id));
		List<SubcontractorType> types = em.createQuery(criteria)
				.getResultList();
		return types.size() > 0 ? types.get(0) : null;
	}

}
