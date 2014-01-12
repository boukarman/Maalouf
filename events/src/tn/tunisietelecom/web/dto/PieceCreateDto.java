package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import tn.tunisietelecom.event.entities.Site;

public class PieceCreateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String labPiece = StringUtils.EMPTY;

	private String establishementDate;

	// @NotBlank(message = "{event.field.required}")
	private String status = StringUtils.EMPTY;

	private String mark = StringUtils.EMPTY;

	private String supplier = StringUtils.EMPTY;

	private Site site;

	private long idLocal;

	public String getLabPiece() {
		return labPiece;
	}

	public void setLabPiece(String labPiece) {
		this.labPiece = labPiece;
	}

	public String getEstablishementDate() {
		return establishementDate;
	}

	public void setEstablishementDate(String establishementDate) {
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

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}