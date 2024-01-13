package modelo.scrabble;

import java.io.Serializable;

public class PremioPalabra implements Casillero, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String letra = "";
	private TipoPuntaje tipoPuntaje;
	
	//CONSTRUCTOR
	public PremioPalabra(TipoPuntaje tipoPuntaje) {
		this.tipoPuntaje = tipoPuntaje;
		if(tipoPuntaje == TipoPuntaje.DOBLE) {
			letra = "2";
			}
		else {
			letra = "3";
		}
	}
	
	//INTERFAZ
	
	public String getDescripcion() {
		return letra;
	}
		
	public int getPuntos() {
		int premio;
		if(tipoPuntaje == TipoPuntaje.DOBLE) {
			premio = 2;
		}
		else {
			premio = 3;
		}
		return premio;
	}
	
}
	

