package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.SubcontractorType;

public interface SubcontractorTypeDao {

	List<SubcontractorType> findAll();

	SubcontractorType findById(long id);
}
