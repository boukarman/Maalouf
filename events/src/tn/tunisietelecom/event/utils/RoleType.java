package tn.tunisietelecom.event.utils;

public enum RoleType {

	BOSSUNIT("4"), EESAGENT("2"), SUBCONTRACTOR("3"), ADMINISTRATOR("1");

	private String type;

	RoleType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
