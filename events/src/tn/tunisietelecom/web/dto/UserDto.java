package tn.tunisietelecom.web.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;

	private String fullName = StringUtils.EMPTY;

	private String role = StringUtils.EMPTY;

	private Date readEventDate;

	private Date startEventDate;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getReadEventDate() {
		return readEventDate;
	}

	public void setReadEventDate(Date readEventDate) {
		this.readEventDate = readEventDate;
	}

	public Date getStartEventDate() {
		return startEventDate;
	}

	public void setStartEventDate(Date startEventDate) {
		this.startEventDate = startEventDate;
	}

}
