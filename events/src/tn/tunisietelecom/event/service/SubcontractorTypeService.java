package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.SubcontractorType;

public interface SubcontractorTypeService {

	List<SubcontractorType> findAll();

	SubcontractorType findById(long id);
}
