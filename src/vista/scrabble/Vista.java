package vista.scrabble;

import java.util.ArrayList;

import modelo.scrabble.Casillero;
import modelo.scrabble.IJugador;
import modelo.scrabble.IPartida;
import modelo.scrabble.Jugador;

public interface Vista {
	
	 void iniciar();
	 void mostrarComenzarPartida(ArrayList<IJugador> jugadores);
	 void mostrarTablero(Casillero[][] tablero);
	 void mostrarEstadoJugador(IJugador jugador);
	 void mostrarPartidasGuardadas(ArrayList<IPartida> partidas);
	 void mostrarRanking();
	 void mostrarMensaje(String mensaje);
	 boolean esTurnoActual();
	 boolean estaConectado();
	
}
