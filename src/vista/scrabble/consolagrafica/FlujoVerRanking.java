package vista.scrabble.consolagrafica;

import java.io.IOException;
import java.util.ArrayList;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;

public class FlujoVerRanking extends Flujo{

	public FlujoVerRanking(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}

	public void mostarMenuTextual() {
		int i = 1;
		String dato = "";
		ArrayList<Jugador> top5jugadores = null;
		try {
			top5jugadores = controlador.obtenerTop5Jugadores();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		vista.mostrarMensaje("*** TOP 5 MEJORES JUGADORES ***");
		vista.mostrarMensaje("");
		vista.mostrarMensaje("Jugador		Puntaje\n");
		for(Jugador j: top5jugadores) {
			dato += (i + ". ") + j.getNombre() + ". ......... " + j.getPuntaje() + ".\n";
		}
		vista.mostrarMensaje(dato);
		vista.mostrarMensaje("Presiona Intro para continuar.");
	}


	public Flujo elegirOpcion(String opcion) {
		return new FlujoMenuPrincipal(vista,controlador);
	}

}
