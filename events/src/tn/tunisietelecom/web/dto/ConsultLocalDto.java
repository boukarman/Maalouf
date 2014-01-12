package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class ConsultLocalDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idLocal;

	private String labLocal = StringUtils.EMPTY;

	private String position = StringUtils.EMPTY;

	private long siteId;

	private String labSite = StringUtils.EMPTY;

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public String getLabLocal() {
		return labLocal;
	}

	public void setLabLocal(String labLocal) {
		this.labLocal = labLocal;
	}

	public long getSiteId() {
		return siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public String getLabSite() {
		return labSite;
	}

	public void setLabSite(String labSite) {
		this.labSite = labSite;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
