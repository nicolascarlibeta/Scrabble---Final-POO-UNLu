package modelo.scrabble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Palabra implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String palabra = "";
	private List<Letra> letras = new ArrayList<>();
	
	public Palabra(String palabra) {
		this.palabra = palabra;
		char[] vectorPalabra = palabra.toCharArray();
		for(Character c: vectorPalabra){
			this.letras.add(new Letra(c + ""));
		}
	}
	
	public List<Letra> getLetras() {
		return letras;
	}

	public String getPalabra() {
		return palabra;
	}
	
	
	
	

}
