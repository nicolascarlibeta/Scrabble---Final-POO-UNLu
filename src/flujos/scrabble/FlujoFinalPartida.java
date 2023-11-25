package flujos.scrabble;

import controlador.scrabble.Controlador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoFinalPartida extends Flujo{

	private int idGanador;
	
	public FlujoFinalPartida(ConsolaGrafica vista, Controlador controlador, int idGanador) {
		super(vista, controlador);
		this.idGanador = idGanador;
	}

	public void mostarMenuTextual() {
		vista.println("El juego ha terminado. ¡Felicidades " + controlador.obtenerJugadores(idGanador).getNombre() + ", sos el ganador!");
		vista.println("");
		vista.println("Presiona Intro para continuar.");
	}

	public Flujo elegirOpcion(String opcion) {
		return new FlujoMenuPrincipal(vista,controlador);
	}

}
