package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.SiteDao;
import tn.tunisietelecom.event.entities.Site;

@Repository
public class SiteDaoImpl implements SiteDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Site site) {
		em.persist(site);
	}

	public void delete(Site site) {
		em.remove(site);
	}

	public List<Site> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Site> criteria = builder.createQuery(Site.class);
		Root<Site> siteRoot = criteria.from(Site.class);
		criteria.select(siteRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<Site> findSitesByPage(int firstResult, int maxResult) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Site> criteria = builder.createQuery(Site.class);
		Root<Site> siteRoot = criteria.from(Site.class);
		criteria.select(siteRoot);

		TypedQuery<Site> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);
		return typedQuery.getResultList();
	}

	public long countSites() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Site> siteRoot = criteria.from(Site.class);
		criteria.select(builder.count(siteRoot));

		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public Site findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Site> criteria = builder.createQuery(Site.class);
		Root<Site> siteRoot = criteria.from(Site.class);
		criteria.select(siteRoot);
		criteria.where(builder.equal(siteRoot.get("siteId"), id));
		List<Site> sites = em.createQuery(criteria).getResultList();
		return sites.size() > 0 ? sites.get(0) : null;
	}

	public void update(Site site) {
		em.merge(site);
	}

}
