package tn.tunisietelecom.event.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Privilege implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idPriv;
	
	private String labPriv;

	/**
	 * @return the idPriv
	 */
	public long getIdPriv() {
		return idPriv;
	}

	/**
	 * @param idPriv the idPriv to set
	 */
	public void setIdPriv(long idPriv) {
		this.idPriv = idPriv;
	}

	/**
	 * @return the labPriv
	 */
	public String getLabPriv() {
		return labPriv;
	}

	/**
	 * @param labPriv the labPriv to set
	 */
	public void setLabPriv(String labPriv) {
		this.labPriv = labPriv;
	}

	
}
