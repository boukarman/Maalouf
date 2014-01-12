package tn.tunisietelecom.event.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Piece implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idPiece;

	private String labPiece;

	private String periodicity;

	private Date establishementDate;

	private Date lastInter;

	private String status;

	private String mark;

	private String supplier;

	@ManyToOne(fetch = FetchType.LAZY)
	private Local local;

	public long getIdPiece() {
		return idPiece;
	}

	public void setIdPiece(long idPiece) {
		this.idPiece = idPiece;
	}

	public String getLabPiece() {
		return labPiece;
	}

	public void setLabPiece(String labPiece) {
		this.labPiece = labPiece;
	}

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}

	public Date getEstablishementDate() {
		return establishementDate;
	}

	public void setEstablishementDate(Date establishementDate) {
		this.establishementDate = establishementDate;
	}

	public Date getLastInter() {
		return lastInter;
	}

	public void setLastInter(Date lastInter) {
		this.lastInter = lastInter;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}
