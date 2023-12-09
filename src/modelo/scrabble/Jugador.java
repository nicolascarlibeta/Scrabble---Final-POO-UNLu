package modelo.scrabble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Serializable{
	
	private static final long serialVersionUID = -1267351262799502699L;
	private String nombre = "";
	private int puntaje = 0;
	private List<Character> atril = new ArrayList<>();
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public List<Character> getAtril() {
		return atril;
	}

	public void setAtril(List<Character> atril) {
		this.atril = atril;
	}

	
	
	
	
}
