package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class AgentChartDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long number;

	private String fullName = StringUtils.EMPTY;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
