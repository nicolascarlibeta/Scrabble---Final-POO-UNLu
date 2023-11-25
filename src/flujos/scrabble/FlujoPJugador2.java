package flujos.scrabble;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoPJugador2 extends FlujoPartida{
	
	public FlujoPJugador2(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador, idJugador);
	}

	public void mostarMenuTextual() {
		super.mostarMenuTextual();
	}

	public Flujo elegirOpcion(String opcion) {
		return super.elegirOpcion(opcion);
	}
	
	public String mostrarEstadoJugador() {
		Jugador jugador2 = controlador.obtenerJugadores(idJugador);
		return "Jugador: " + jugador2.getNombre() + "\n"
				+ "ATRIL: " + jugador2.getAtril() + "\n"
				+ "PUNTAJE: " + jugador2.getPuntaje() + "\n";
	}

	public Flujo avanzarFlujo() {
		return new FlujoOpJugador1(vista,controlador,0);
	}
	

}
