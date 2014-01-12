package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.Cause;

public interface CauseService {

	void addCause(Cause cause);

	List<Cause> findAllCauses();

	Cause findById(long id);
}
