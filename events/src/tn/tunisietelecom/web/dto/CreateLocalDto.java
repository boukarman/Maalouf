package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import tn.tunisietelecom.event.entities.Site;

public class CreateLocalDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String labLocal = StringUtils.EMPTY;

	private String position = StringUtils.EMPTY;

	private Site site;

	public String getLabLocal() {
		return labLocal;
	}

	public void setLabLocal(String labLocal) {
		this.labLocal = labLocal;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
