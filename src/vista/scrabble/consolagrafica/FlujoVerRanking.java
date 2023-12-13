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
		vista.mostrarRanking();
		vista.mostrarMensaje("Presiona Intro para continuar.");
	}


	public Flujo elegirOpcion(String opcion) {
		return new FlujoMenuPrincipal(vista,controlador);
	}

}
