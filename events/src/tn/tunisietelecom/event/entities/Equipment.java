package tn.tunisietelecom.event.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Equipment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idEquipment;

	private String labEquipment;

	private String status;

	private String delegation;

	private String mark;

	private String supplier;

	private Date establishmentServiceDate;

	/**
	 * @return the idEquipment
	 */
	public long getIdEquipment() {
		return idEquipment;
	}

	/**
	 * @param idEquipment
	 *            the idEquipment to set
	 */
	public void setIdEquipment(long idEquipment) {
		this.idEquipment = idEquipment;
	}

	/**
	 * @return the labEquipment
	 */
	public String getLabEquipment() {
		return labEquipment;
	}

	/**
	 * @param labEquipment
	 *            the labEquipment to set
	 */
	public void setLabEquipment(String labEquipment) {
		this.labEquipment = labEquipment;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the delegation
	 */
	public String getDelegation() {
		return delegation;
	}

	/**
	 * @param delegation
	 *            the delegation to set
	 */
	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}

	/**
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * @param mark
	 *            the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier
	 *            the supplier to set
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the establishmentServiceDate
	 */
	public Date getEstablishmentServiceDate() {
		return establishmentServiceDate;
	}

	/**
	 * @param establishmentServiceDate
	 *            the establishmentServiceDate to set
	 */
	public void setEstablishmentServiceDate(Date establishmentServiceDate) {
		this.establishmentServiceDate = establishmentServiceDate;
	}

}
