package tn.tunisietelecom.event.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserRoleSubcontractorTypeId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User user;

	@ManyToOne
	private Role role;

	public UserRoleSubcontractorTypeId() {

	}

	public UserRoleSubcontractorTypeId(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
