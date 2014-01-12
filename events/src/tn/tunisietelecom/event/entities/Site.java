package tn.tunisietelecom.event.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Site implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long siteId;

	private String labSite;

	private String address;

	@OneToMany
	private List<Local> locals;

	public Site() {

	}

	public Site(String labSite, String address) {
		this.labSite = labSite;
		this.address = address;
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

	public List<Local> getLocals() {
		return locals;
	}

	public void setLocals(List<Local> locals) {
		this.locals = locals;
	}

}
