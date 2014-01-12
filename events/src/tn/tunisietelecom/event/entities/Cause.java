package tn.tunisietelecom.event.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cause implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long causeId;

	private String causeDesc;

	public long getCauseId() {
		return causeId;
	}

	public void setCauseId(long causeId) {
		this.causeId = causeId;
	}

	public String getCauseDesc() {
		return causeDesc;
	}

	public void setCauseDesc(String causeDesc) {
		this.causeDesc = causeDesc;
	}

}
