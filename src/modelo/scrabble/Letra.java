package modelo.scrabble;

import java.io.Serializable;
import java.util.Objects;

public class Letra implements Casillero, Serializable{

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
	
	public String toString() {
		return getDescripcion();
	}
	
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Letra ltr = (Letra) obj;
        return Objects.equals(this.letra, ltr.letra);
	}
	
	public void setDescripcion(String descripcion) {
		this.letra = descripcion;
	}


}
