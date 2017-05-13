package it.polito.tdp.metrodeparis.model;

import org.jgrapht.graph.DefaultWeightedEdge;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Linea extends DefaultWeightedEdge{
	private static int idCollegamento=0;
	private int idCollegamento1=0;
	private int id;
	private int intervallo;
	private int velocita;
	private double tempo=0;
	private int flag=0;
	public Linea() {
		super();
		Linea.idCollegamento ++;
		this.idCollegamento1=Linea.idCollegamento;
	}

	/**
	 * @return the idCollegamento
	 */
	public int getIdCollegamento() {
		return idCollegamento1;
	}

	/**
	 * @param idCollegamento the idCollegamento to set
	 */
	public void setIdCollegamento(int idCollegamento) {
		Linea.idCollegamento = idCollegamento;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the intervallo
	 */
	public int getIntervallo() {
		return intervallo;
	}

	/**
	 * @param intervallo the intervallo to set
	 */
	public void setIntervallo(int intervallo) {
		this.intervallo = intervallo;
	}

	/**
	 * @return the velocita
	 */
	public int getVelocita() {
		return velocita;
	}

	/**
	 * @param velocita the velocita to set
	 */
	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Linea [idCollegamento=" + idCollegamento1 + ", id=" + id + ", intervallo=" + intervallo + ", velocita="
				+ velocita + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idCollegamento1;
		result = prime * result + intervallo;
		long temp;
		temp = Double.doubleToLongBits(tempo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + velocita;
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
		Linea other = (Linea) obj;
		if (id != other.id)
			return false;
		if (idCollegamento1 != other.idCollegamento1)
			return false;
		if (intervallo != other.intervallo)
			return false;
		if (Double.doubleToLongBits(tempo) != Double.doubleToLongBits(other.tempo))
			return false;
		if (velocita != other.velocita)
			return false;
		return true;
	}

	public void setTempo(Fermata fermata1,Fermata fermata2) {
		double d=LatLngTool.distance(fermata1.getCoords(), fermata2.getCoords(), LengthUnit.KILOMETER);
		
			this.tempo=30/1000+ d/velocita;
		}
	
	public double getTempo() {
	return tempo;
		}

	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	
	
}
