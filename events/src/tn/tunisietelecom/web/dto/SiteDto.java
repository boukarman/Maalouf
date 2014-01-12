package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class SiteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long siteId;

	private String labSite = StringUtils.EMPTY;

	private String address = StringUtils.EMPTY;

	public SiteDto() {
		// TODO Auto-generated constructor stub
	}

	public SiteDto(long siteId, String labSite) {
		this.siteId = siteId;
		this.labSite = labSite;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
