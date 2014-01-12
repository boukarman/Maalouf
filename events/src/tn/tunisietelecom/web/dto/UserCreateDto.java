package tn.tunisietelecom.web.dto;

import java.io.Serializable;

public class UserCreateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;

	private String password;

	private String lastName;

	private String firstName;

	private String address;

	private String email;

	private String telNumber;

	private String formation;

	private long idRole;

	private long subcontractorTypeId;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public long getSubcontractorTypeId() {
		return subcontractorTypeId;
	}

	public void setSubcontractorTypeId(long subcontractorTypeId) {
		this.subcontractorTypeId = subcontractorTypeId;
	}

}
