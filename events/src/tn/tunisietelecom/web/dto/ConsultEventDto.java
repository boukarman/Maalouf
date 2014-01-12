package tn.tunisietelecom.web.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ConsultEventDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long idEvent;
	
	private long status;
	
	private long ownStatus;
	
	private String site = StringUtils.EMPTY;
	
	private String local = StringUtils.EMPTY;
	
	private String piece = StringUtils.EMPTY;
	
	private String cause = StringUtils.EMPTY;
	
	private String description = StringUtils.EMPTY;
	
	private String responsible = StringUtils.EMPTY;
	
	private Date date = null;
	
	private Long workingInUserId;
	
	public long getIdEvent() {
		return idEvent;
	}
	
	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}
	
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
	
	public String getResponsible() {
		return responsible;
	}
	
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public long getStatus() {
		return status;
	}
	
	public void setStatus(long status) {
		this.status = status;
	}
	
	public Long getWorkingInUserId() {
		return workingInUserId;
	}
	
	public void setWorkingInUserId(Long workingInUserId) {
		this.workingInUserId = workingInUserId;
	}

	public long getOwnStatus() {
		return ownStatus;
	}

	public void setOwnStatus(long ownStatus) {
		this.ownStatus = ownStatus;
	}
	
}
