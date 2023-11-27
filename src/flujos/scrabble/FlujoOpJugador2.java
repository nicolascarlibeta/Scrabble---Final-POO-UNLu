package flujos.scrabble;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoOpJugador2 extends FlujoOpcionesJuego{
	
	private int idJugador;
	
	public FlujoOpJugador2(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador);
		this.idJugador = idJugador;
	}

	public Flujo avanzarFlujo() {
		vista.mostrarTablero(controlador.obtenerTablero());
		return new FlujoOpJugador1(vista,controlador,0);
	}

	public Flujo ingresarPalabra() {
		return new FlujoPJugador2(vista,controlador,idJugador);
	}

	public String mostrarEstadoJugador() {
		Jugador jugador2 = controlador.obtenerJugadores(idJugador);
		return "Jugador: " + jugador2.getNombre() + "\n"
				+ "ATRIL: " + jugador2.getAtril() + "\n"
				+ "PUNTAJE: " + jugador2.getPuntaje() + "\n";
	}

	public Flujo cambiarFichas() {
		return new FlujoCFJugador2(vista,controlador,idJugador);
	}

}
