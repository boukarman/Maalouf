package tn.tunisietelecom.event.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.PrivilegeDao;
import tn.tunisietelecom.event.entities.Privilege;

@Repository
public class PrivilegeDaoImpl implements PrivilegeDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Privilege privilege) {
		em.persist(privilege);
	}

}
