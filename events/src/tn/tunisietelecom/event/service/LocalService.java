package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.Local;

public interface LocalService {

	void addLocal(Local local);

	List<Local> findAllLocals();

	List<Local> findLocalsByPage(int firstResult, int maxResult);

	Local findById(long id);

	void deleteLocal(Local local);

	void update(Local local);

	long countLocals();
}
