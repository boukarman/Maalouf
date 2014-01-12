package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.RoleDao;
import tn.tunisietelecom.event.entities.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager em;

	public Role findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		Root<Role> roleRoot = criteria.from(Role.class);
		criteria.select(roleRoot);
		criteria.where(builder.equal(roleRoot.get("idRole"), id));
		List<Role> roles = em.createQuery(criteria).getResultList();
		return roles.size() > 0 ? roles.get(0) : null;
	}

	public List<Role> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		Root<Role> roleRoot = criteria.from(Role.class);
		criteria.select(roleRoot);

		return em.createQuery(criteria).getResultList();
	}

}
