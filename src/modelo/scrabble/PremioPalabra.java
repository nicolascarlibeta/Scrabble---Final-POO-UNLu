package modelo.scrabble;

public class PremioPalabra implements Ficha{

	private String letra = "";
	private TipoPuntaje tipoPuntaje;
	
	//CONSTRUCTOR
	public PremioPalabra(TipoPuntaje tipoPuntaje) {
		this.tipoPuntaje = tipoPuntaje;
		if(tipoPuntaje == TipoPuntaje.DOBLE) {
			letra = "2";
			}
		else {
			letra = "3";
		}
	}
	
	//INTERFAZ
	
	public String getLetra() {
		return letra;
	}
		
	public int getPuntos() {
		int premio;
		if(tipoPuntaje == TipoPuntaje.DOBLE) {
			premio = 2;
		}
		else {
			premio = 3;
		}
		return premio;
	}
	
	

	

}
