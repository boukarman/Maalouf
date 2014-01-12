package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.UserDao;
import tn.tunisietelecom.event.entities.Role;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.utils.RoleType;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	public List<User> findByLogin(String login) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		criteria.where(builder.equal(userRoot.get("login"), login));

		return em.createQuery(criteria).getResultList();
	}

	public List<User> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<User> findEESAgent() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		Predicate p1 = builder.equal(userRoot.get("role").get("idRole"),
				RoleType.EESAGENT.getType());

		criteria.where(p1);
		return em.createQuery(criteria).getResultList();
	}

	public List<User> findEESAgentAndSubcontrator() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		Predicate p1 = builder.equal(userRoot.get("role").get("idRole"),
				RoleType.EESAGENT.getType());
		Predicate p2 = builder.equal(userRoot.get("role").get("idRole"),
				RoleType.SUBCONTRACTOR.getType());

		criteria.where(builder.or(p1, p2));
		return em.createQuery(criteria).getResultList();
	}

	public User findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		criteria.where(builder.equal(userRoot.get("userId"), id));
		List<User> users = em.createQuery(criteria).getResultList();
		return users.size() > 0 ? users.get(0) : null;
	}

	public User save(User user) {
		em.persist(user);
		em.flush();
		return user;
	}

	public void update(User user) {
		em.merge(user);
	}

	public List<SubcontractorType> findAllSubcontractorType() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<SubcontractorType> criteria = builder
				.createQuery(SubcontractorType.class);
		Root<SubcontractorType> subcontractorTypeRoot = criteria
				.from(SubcontractorType.class);
		criteria.select(subcontractorTypeRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<Role> findAllRoles() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		Root<Role> roleRoot = criteria.from(Role.class);
		criteria.select(roleRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<User> findUsersByPage(int firstResult, int maxResult) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);

		TypedQuery<User> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);
		return typedQuery.getResultList();
	}

	public long countUsers() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(builder.count(userRoot));

		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();
	}

	public void delete(User user) {
		em.remove(user);
	}

}
