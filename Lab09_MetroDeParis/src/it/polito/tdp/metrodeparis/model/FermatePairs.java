package it.polito.tdp.metrodeparis.model;
import org.jgrapht.graph.DefaultWeightedEdge;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.model.Fermata;

public class FermatePairs extends DefaultWeightedEdge{

	
	private Fermata fermata1;
	private Fermata fermata2;
	private int id;
	private int intervallo;
	private int velocita;
	
	public FermatePairs(){
		super();
	}
	public FermatePairs(Fermata fermata1, Fermata fermata2, int id, int intervallo, int velocita) {
		super();
		this.fermata1 = fermata1;
		this.fermata2 = fermata2;
		this.id = id;
		this.intervallo = intervallo;
		this.velocita = velocita;
	}
	public Fermata getFermata1() {
		return fermata1;
	}
	public void setFermata1(Fermata fermata1) {
		this.fermata1 = fermata1;
	}
	public Fermata getFermata2() {
		return fermata2;
	}
	public void setFermata2(Fermata fermata2) {
		this.fermata2 = fermata2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIntervallo() {
		return intervallo;
	}
	public void setIntervallo(int intervallo) {
		this.intervallo = intervallo;
	}
	public int getVelocita() {
		return velocita;
	}
	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fermata1 == null) ? 0 : fermata1.hashCode());
		result = prime * result + ((fermata2 == null) ? 0 : fermata2.hashCode());
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FermatePairs other = (FermatePairs) obj;
		if (fermata1 == null) {
			if (other.fermata1 != null)
				return false;
		} else if (!fermata1.equals(other.fermata1))
			return false;
		if (fermata2 == null) {
			if (other.fermata2 != null)
				return false;
		} else if (!fermata2.equals(other.fermata2))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FermatePairs [fermata1=" + fermata1 + ", fermata2=" + fermata2 + ", id=" + id + ", intervallo="
				+ intervallo + ", velocita=" + velocita + "]";
	}
	public double getTempo() {
	double d=LatLngTool.distance(fermata1.getCoords(), fermata2.getCoords(), LengthUnit.KILOMETER);
	
		return d/velocita;
	}

	
	
	
}