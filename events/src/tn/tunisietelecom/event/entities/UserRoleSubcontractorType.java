package tn.tunisietelecom.event.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserRoleSubcontractorType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private UserRoleSubcontractorTypeId id;

	@OneToOne
	private SubcontractorType type;

	public UserRoleSubcontractorType() {
		// TODO Auto-generated constructor stub
	}

	public UserRoleSubcontractorType(User user, Role role) {
		this.id = new UserRoleSubcontractorTypeId(user, role);
	}

	public UserRoleSubcontractorTypeId getId() {
		return id;
	}

	public void setId(UserRoleSubcontractorTypeId id) {
		this.id = id;
	}

	public SubcontractorType getType() {
		return type;
	}

	public void setType(SubcontractorType type) {
		this.type = type;
	}

}
