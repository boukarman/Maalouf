package tn.tunisietelecom.event.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Local implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idLocal;

	private String labLocal;

	private String position;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Piece> pieces;

	@ManyToOne(fetch = FetchType.LAZY)
	private Site site;

	/**
	 * @return the idLocal
	 */
	public long getIdLocal() {
		return idLocal;
	}

	/**
	 * @param idLocal
	 *            the idLocal to set
	 */
	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	/**
	 * @return the labLocal
	 */
	public String getLabLocal() {
		return labLocal;
	}

	/**
	 * @param labLocal
	 *            the labLocal to set
	 */
	public void setLabLocal(String labLocal) {
		this.labLocal = labLocal;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}
