package vista.scrabble.consolagrafica;

import java.io.IOException;
import java.util.ArrayList;

import controlador.scrabble.Controlador;
import modelo.scrabble.Partida;

public class FlujoElegirPartida extends Flujo{
	
	private int idJugador = controlador.obtenerTurnoActual();

	public FlujoElegirPartida(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}

	public void mostarMenuTextual() {
		vista.mostrarPartidasGuardadas(new Object());
		vista.mostrarMensaje("Seleccione una partida: ");
	}

	public Flujo elegirOpcion(String opcion) {
		int idPartida = 1;
		try {
			idPartida = Integer.parseInt(opcion);
			if(idPartida < 1 || idPartida > controlador.obtenerPartidas().size()) {
				vista.mostrarMensaje("Ingrese un número que corresponda al ID de la partida.");
				return this;
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		catch (NumberFormatException e) {
			vista.mostrarMensaje("Ingrese un número que corresponda al ID de la partida.");
		}
		try {
			controlador.cargarPartida(idPartida - 1);
			return new FlujoOpcionesJuego(vista,controlador);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			return this;
		}
	}

}
