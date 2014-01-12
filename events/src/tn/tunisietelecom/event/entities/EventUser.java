package tn.tunisietelecom.event.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EventUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private EventUserId id;

	private long status = 0;

	private Date recievedDate;

	private Date readDate;

	private Date startDate;

	private Date finishedDate;

	public EventUser() {

	}

	public EventUser(User user, Event event) {
		this.id = new EventUserId(user, event);
	}

	public EventUserId getId() {
		return id;
	}

	public void setId(EventUserId id) {
		this.id = id;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
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
