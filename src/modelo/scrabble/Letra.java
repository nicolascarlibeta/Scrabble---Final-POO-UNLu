package modelo.scrabble;

public class Letra extends Ficha{

	private String letra;
	private int puntos;
	
	//CONSTRUCTOR
	public Letra(String letra, int puntos) {
		this.letra = letra;
		this.puntos = puntos;
	}
	
	public String getLetra() {
		return letra;
	}
	
	public int getPuntos() {
		return puntos;
	}

	public Puntaje getTipoPuntaje() {
		return null;
	}

	@Override
	public int getPremioLetra() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getPremioPalabra() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public String toString() {
		return "LETRA: " + letra + "\n"
			 + "PUNTOS: " + puntos + "\n";
	}




}
