package modelo.scrabble;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	
	private String nombre = "";
	private int puntaje = 0;
	private List<Character> atril = new ArrayList<>();
	private List<Palabra> palabrasAsociadas = new ArrayList<>();
	
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

	public List<Palabra> getPalabrasAsociadas() {
		return palabrasAsociadas;
	}

	public void setPalabrasAsociadas(List<Palabra> palabrasAsociadas) {
		this.palabrasAsociadas = palabrasAsociadas;
	}

	public String toString(){
		String dato = "";
		for(int i = 0; i < atril.size(); i++) {
			dato += atril.get(i) + " "; 
		}
		return dato;
	}
	
	
	

	
	
	
}
