package ar.edu.unlu.poo.scrabble;

public class PremioLetra extends Ficha{
	
	private final String letra = "L";
	private Puntaje tipoPuntaje;
	
	public String getLetra() {
		return letra + tipoPuntaje.getDescripcion();
	}
	
	public PremioLetra(Puntaje tipoPuntaje) {
		this.tipoPuntaje = tipoPuntaje;
	}
	
	public int getPremioLetra() {
		return tipoPuntaje.getPremio();
	}
	
	public int getPremioPalabra() {
		return 1;
	}

	public int getPuntos() {
		return 0;
	}
	
}
