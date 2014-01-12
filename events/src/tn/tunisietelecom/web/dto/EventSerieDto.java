package tn.tunisietelecom.web.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class EventSerieDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String site = StringUtils.EMPTY;

	private String local = StringUtils.EMPTY;

	private String piece = StringUtils.EMPTY;

	private String cause = StringUtils.EMPTY;

	private String description = StringUtils.EMPTY;

	private String workingInUser = StringUtils.EMPTY;

	private Date startDate;

	private Date finishedDate;

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorkingInUser() {
		return workingInUser;
	}

	public void setWorkingInUser(String workingInUser) {
		this.workingInUser = workingInUser;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

}
