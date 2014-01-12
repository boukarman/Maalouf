package tn.tunisietelecom.event.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.EquipmentDao;

import tn.tunisietelecom.event.entities.Equipment;

import tn.tunisietelecom.event.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;

	@Transactional
	public void addEquipment(Equipment equipment) {
		this.equipmentDao.save(equipment);

	}

}
