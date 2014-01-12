package tn.tunisietelecom.web.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PerformanceChartDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String criteria = StringUtils.EMPTY;
	
	private Integer number;
	
	private List<Long> events;
	
	public PerformanceChartDto() {
		
	}
	
	public PerformanceChartDto(String criteria, Integer number) {
		this.criteria = criteria;
		this.number = number;
	}
	
	public String getCriteria() {
		return criteria;
	}
	
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public List<Long> getEvents() {
		return events;
	}
	
	public void setEvents(List<Long> events) {
		this.events = events;
	}
	
}
