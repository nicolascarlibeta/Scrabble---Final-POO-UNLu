package flujos.scrabble;

import controlador.scrabble.Controlador;
import flujos.scrabble.FlujoIngresarJugadores.EstadosPosibles;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoPJugador1 extends FlujoPartida{
	
	public FlujoPJugador1(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador, idJugador);
	}

	public void mostarMenuTextual() {
		super.mostarMenuTextual();
	}

	public Flujo elegirOpcion(String opcion) {
		return super.elegirOpcion(opcion);
	}

	public String mostrarEstadoJugador() {
		Jugador jugador1 = controlador.obtenerJugadores(idJugador);
		return "Jugador: " + jugador1.getNombre() + "\n"
				+ "ATRIL: " + jugador1.getAtril() + "\n"
				+ "PUNTAJE: " + jugador1.getPuntaje() + "\n";
	}
	
	public Flujo avanzarFlujo() {
		return new FlujoOpJugador2(vista,controlador,1);
	}
	
	
	
	
}


	