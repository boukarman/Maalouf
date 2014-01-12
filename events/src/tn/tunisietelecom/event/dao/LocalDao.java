package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.Local;

public interface LocalDao {

	void save(Local local);

	List<Local> findAll();

	List<Local> findLocalsByPage(int firstResult, int maxResult);

	Local findById(long id);

	void delete(Local local);

	void update(Local local);

	long countLocals();

}
