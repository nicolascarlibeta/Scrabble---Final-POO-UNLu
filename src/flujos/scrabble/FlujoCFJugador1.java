package flujos.scrabble;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoCFJugador1 extends FlujoCambiarFichas{

	public FlujoCFJugador1(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador, idJugador);
	}

	public Flujo avanzarFlujo() {
		return new FlujoOpJugador2(vista,controlador,1);
	}
	
	public String mostrarEstadoJugador() {
		Jugador jugador1 = controlador.obtenerJugadores(0);
		return "Jugador: " + jugador1.getNombre() + "\n"
				+ "ATRIL: " + jugador1.getAtril() + "\n"
				+ "PUNTAJE: " + jugador1.getPuntaje() + "\n";
	}

}
