package tn.tunisietelecom.event.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.InterferenceDao;
import tn.tunisietelecom.event.entities.Interference;

@Repository
public class InterferenceDaoImpl implements InterferenceDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Interference interference) {
		em.persist(interference);
	}
}
