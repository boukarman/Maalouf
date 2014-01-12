package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.Cause;

public interface CauseDao {

	void save(Cause cause);

	List<Cause> findAll();

	Cause findById(long id);

}
