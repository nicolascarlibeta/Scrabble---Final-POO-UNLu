package modelo.scrabble;

import java.io.Serializable;

public class Letra implements Casillero, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String letra = "";
	private int puntos;
	
	//CONSTRUCTOR
	public Letra(String letra) {
		this.letra = letra;
		if(PuntajeFichas.getPuntaje(letra) != 0) {
			this.puntos = PuntajeFichas.getPuntaje(letra);
		}
	}
	
	public String getDescripcion() {
		return letra;
	}
	
	public int getPuntos() {
		return puntos;
	}


}
