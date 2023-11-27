package obs.scrabble;

import modelo.scrabble.Evento;

public interface Observador {
	
	public abstract void actualizar(Evento evento);
	

}
