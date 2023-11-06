package ar.edu.unlu.poo.scrabble;

import java.util.ArrayList;

public class Jugador {
	
	private String nombre = "";
	private int puntos = 0;
	private ArrayList<Palabra> palabras = new ArrayList<>();
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public boolean addPalabra(Palabra palabra) {
		return palabras.add(palabra);
	}
	
	
	
}
