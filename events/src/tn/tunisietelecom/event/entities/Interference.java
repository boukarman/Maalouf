package tn.tunisietelecom.event.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Interference implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idInter;

	private String causeInter;

	private String decision;

	private Date startDate;

	private Date endDate;

	/**
	 * @return the idInter
	 */
	public long getIdInter() {
		return idInter;
	}

	/**
	 * @param idInter
	 *            the idInter to set
	 */
	public void setIdInter(long idInter) {
		this.idInter = idInter;
	}

	/**
	 * @return the causeInter
	 */
	public String getCauseInter() {
		return causeInter;
	}

	/**
	 * @param causeInter
	 *            the causeInter to set
	 */
	public void setCauseInter(String causeInter) {
		this.causeInter = causeInter;
	}

	/**
	 * @return the decision
	 */
	public String getDecision() {
		return decision;
	}

	/**
	 * @param decision
	 *            the decision to set
	 */
	public void setDecision(String decision) {
		this.decision = decision;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
