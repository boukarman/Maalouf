package tn.tunisietelecom.web.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import tn.tunisietelecom.event.entities.Cause;
import tn.tunisietelecom.event.entities.Local;
import tn.tunisietelecom.event.entities.Piece;
import tn.tunisietelecom.event.entities.Site;
import tn.tunisietelecom.event.entities.SubcontractorType;
import tn.tunisietelecom.event.entities.User;

public class CreateEventDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date eventDate = new Date();

	private Site site;

	private Local local;

	private Piece piece;

	private Cause cause;

	private User responsible;

	private SubcontractorType subcontractorType;

	private String description;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SubcontractorType getSubcontractorType() {
		return subcontractorType;
	}

	public void setSubcontractorType(SubcontractorType subcontractorType) {
		this.subcontractorType = subcontractorType;
	}

}
