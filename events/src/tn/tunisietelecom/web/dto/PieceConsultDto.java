package tn.tunisietelecom.web.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class PieceConsultDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idPiece;

	private String labPiece = StringUtils.EMPTY;

	private Date establishementDate;

	private String status = StringUtils.EMPTY;

	private String mark = StringUtils.EMPTY;

	private String supplier = StringUtils.EMPTY;

	private String labSite = StringUtils.EMPTY;

	private String labLocal = StringUtils.EMPTY;

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

	public Date getEstablishementDate() {
		return establishementDate;
	}

	public void setEstablishementDate(Date establishementDate) {
		this.establishementDate = establishementDate;
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

	public String getLabSite() {
		return labSite;
	}

	public void setLabSite(String labSite) {
		this.labSite = labSite;
	}

	public String getLabLocal() {
		return labLocal;
	}

	public void setLabLocal(String labLocal) {
		this.labLocal = labLocal;
	}

}
