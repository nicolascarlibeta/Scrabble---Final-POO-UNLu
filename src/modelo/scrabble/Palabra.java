package modelo.scrabble;

import java.io.Serializable;
import java.util.ArrayList;

public class Palabra implements Serializable{
	
	private String palabra = "";
	private char[] letras = new char[7];
	
	public Palabra(String palabra) {
		this.palabra = palabra;
		this.letras = palabra.toCharArray();
	}
	
	public char[] getLetras() {
		return letras;
	}

	public String getPalabra() {
		return palabra;
	}
	
	
	

}
