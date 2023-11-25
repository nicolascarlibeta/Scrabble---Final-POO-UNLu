package obs.scrabble;

import modelo.scrabble.Evento;

public interface Observado {

	public abstract void ligar(Observador o);
	public abstract void desligar(Observador o);
	public abstract void notificar(Evento evento);
	
}
