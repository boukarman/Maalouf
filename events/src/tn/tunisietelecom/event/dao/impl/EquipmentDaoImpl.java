package tn.tunisietelecom.event.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.entities.Equipment;
import tn.tunisietelecom.event.dao.EquipmentDao;

@Repository
public class EquipmentDaoImpl implements EquipmentDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Equipment equipment) {
		em.persist(equipment);
	}

}
