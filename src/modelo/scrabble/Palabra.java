package modelo.scrabble;

import java.util.ArrayList;

public class Palabra {
	
	private char[] letras = new char[7];
	private int puntaje;
	
	public Palabra(char[] letras) {
		this.letras = letras;
	}
	
	public char[] getLetras() {
		return letras;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	
	
	

}
