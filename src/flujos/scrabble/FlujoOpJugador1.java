package flujos.scrabble;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoOpJugador1 extends FlujoOpcionesJuego{

	private int idJugador;
	
	public FlujoOpJugador1(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador);
		this.idJugador = idJugador;
	}

	public Flujo avanzarFlujo() {
		vista.mostrarTablero(controlador.obtenerTablero());
		return new FlujoOpJugador2(vista,controlador,1);
	}

	public Flujo ingresarPalabra() {
		return new FlujoPJugador1(vista,controlador,idJugador);
	}

	public String mostrarEstadoJugador() {
		Jugador jugador1 = controlador.obtenerJugadores(0);
		return "Jugador: " + jugador1.getNombre() + "\n"
				+ "ATRIL: " + jugador1.getAtril() + "\n"
				+ "PUNTAJE: " + jugador1.getPuntaje() + "\n";
	}

	public Flujo cambiarFichas() {
		return new FlujoCFJugador1(vista,controlador,idJugador);
	}

}
