package modelo.scrabble;

public class Letra implements Ficha{

	private String letra;
	private int puntos;
	
	//CONSTRUCTOR
	public Letra(String letra) {
		this.letra = letra;
		if(PuntajeFichas.getPuntaje(letra) != 0) {
			this.puntos = PuntajeFichas.getPuntaje(letra);
		}
	}
	
	public String getLetra() {
		return letra;
	}
	
	public int getPuntos() {
		return puntos;
	}


}
