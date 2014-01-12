package tn.tunisietelecom.event.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.DepartementDao;

@Repository
public class DepartementDaoImpl implements DepartementDao {

	@PersistenceContext
	private EntityManager em;

}
