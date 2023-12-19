package vista.scrabble;

import java.util.ArrayList;

import modelo.scrabble.Ficha;
import modelo.scrabble.Jugador;
import modelo.scrabble.Partida;

public interface Vista {
	
	 void iniciar();
	 void mostrarIngresarJugadores();
	 void mostrarComenzarPartida(Jugador[] jugadores);
	 void mostrarTablero(Ficha[][] tablero);
	 void mostrarEstadoJugador(Jugador jugador);
	 void mostrarPartidasGuardadas(ArrayList<Partida> partidas);
	 void mostrarRanking();
	 void mostrarMensaje(String mensaje);
	
}
