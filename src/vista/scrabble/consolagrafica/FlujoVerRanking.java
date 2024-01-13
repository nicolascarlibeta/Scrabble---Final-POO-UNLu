package vista.scrabble.consolagrafica;

import controlador.scrabble.Controlador;

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
