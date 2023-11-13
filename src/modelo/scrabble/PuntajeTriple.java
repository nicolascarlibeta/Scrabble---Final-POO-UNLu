package modelo.scrabble;

public class PuntajeTriple implements Puntaje{
	
	private final String descripcion = "T";
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getPremio() {
		return 3;
	}
	
}
