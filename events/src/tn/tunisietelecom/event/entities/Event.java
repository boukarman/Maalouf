package tn.tunisietelecom.event.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idEvent;

	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateEvent;

	@OneToMany(cascade = CascadeType.ALL)
	private List<EventUser> eventUserStatus = new ArrayList<EventUser>();

	@ManyToMany(fetch = FetchType.LAZY)
	private List<User> notifiedUsers = new ArrayList<User>();

	@OneToOne(fetch = FetchType.LAZY)
	private User workingInUser;

	@OneToOne(fetch = FetchType.LAZY)
	private User creator;

	@OneToOne(fetch = FetchType.LAZY)
	private Piece piece;

	@OneToOne(fetch = FetchType.LAZY)
	private User responsible;

	@OneToOne(fetch = FetchType.LAZY)
	private Cause cause;

	private String description;

	public long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

	public List<EventUser> getEventUserStatus() {
		return eventUserStatus;
	}

	public void setEventUserStatus(List<EventUser> eventUserStatus) {
		this.eventUserStatus = eventUserStatus;
	}

	public List<User> getNotifiedUsers() {
		return notifiedUsers;
	}

	public void setNotifiedUsers(List<User> notifiedUsers) {
		this.notifiedUsers = notifiedUsers;
	}

	public User getWorkingInUser() {
		return workingInUser;
	}

	public void setWorkingInUser(User workingInUser) {
		this.workingInUser = workingInUser;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

}
