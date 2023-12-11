package vista.scrabble;

import modelo.scrabble.Ficha;
import modelo.scrabble.Jugador;

public interface Vista {
	
	 void iniciar();
	 void mostrarIngresarJugadores();
	 void mostrarComenzarPartida(Jugador[] jugadores);
	 void mostrarTablero(Ficha[][] tablero);
	 void mostrarEstadoJugador(Jugador jugador);
	 void mostrarPartidasGuardadas();
	 void mostrarRanking();
	 void mostrarMensaje(String mensaje);
	
}
