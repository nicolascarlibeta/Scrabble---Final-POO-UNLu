package flujos.scrabble;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoCFJugador2 extends FlujoCambiarFichas{

	public FlujoCFJugador2(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador, idJugador);
	}

	public Flujo avanzarFlujo() {
		return new FlujoOpJugador1(vista,controlador,0);
	}
	
	public String mostrarEstadoJugador() {
		Jugador jugador2 = controlador.obtenerJugadores(idJugador);
		return "Jugador: " + jugador2.getNombre() + "\n"
				+ "ATRIL: " + jugador2.getAtril() + "\n"
				+ "PUNTAJE: " + jugador2.getPuntaje() + "\n";
	}

}
