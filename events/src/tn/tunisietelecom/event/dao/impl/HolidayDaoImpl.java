package tn.tunisietelecom.event.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.HolidayDao;
import tn.tunisietelecom.event.entities.Holiday;

@Repository
public class HolidayDaoImpl implements HolidayDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Holiday holiday) {
		em.persist(holiday);
	}

}
