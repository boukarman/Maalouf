package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class LocalDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idLocal;

	private String labLocal = StringUtils.EMPTY;

	public LocalDto() {
		// TODO Auto-generated constructor stub
	}

	public LocalDto(long idLocal, String labLocal) {
		this.idLocal = idLocal;
		this.labLocal = labLocal;
	}

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

}
