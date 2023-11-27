package vista.scrabble;

import modelo.scrabble.Ficha;
import modelo.scrabble.Jugador;

public interface Vista {
	
	public abstract void mostrarMenuPrincipal();
	public abstract void mostrarIngresarJugadores(Jugador[] jugadores);
	public abstract void mostrarComenzarPartida(Jugador[] jugadores);
	public abstract void mostrarTablero(Ficha[][] tablero);
	public abstract void mostrarMensaje(String mensaje);
	
}
