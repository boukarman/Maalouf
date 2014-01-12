package tn.tunisietelecom.web.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class PieceChartDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long number;

	private String piece = StringUtils.EMPTY;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

}
