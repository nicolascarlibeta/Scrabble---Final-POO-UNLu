package modelo.scrabble;

public class PremioPalabra extends Ficha{

	private final String letra = "P";
	private Puntaje tipoPuntaje;
	
	public String getLetra() {
		return letra + tipoPuntaje.getDescripcion();
	}
	
	public PremioPalabra(Puntaje tipoPuntaje) {
		this.tipoPuntaje = tipoPuntaje;
	}

	public int getPremioLetra() {
		return 1;
	}
	
	public int getPremioPalabra() {
		return tipoPuntaje.getPremio();
	}

	@Override
	public int getPuntos() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	

}
