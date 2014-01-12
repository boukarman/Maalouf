package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class CauseChartDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long number;

	private String cause = StringUtils.EMPTY;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

}
