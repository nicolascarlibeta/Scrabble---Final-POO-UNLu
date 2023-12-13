package vista.scrabble.consolagrafica;

import controlador.scrabble.Controlador;
import modelo.scrabble.Diccionario;

public class FlujoIngresarCantidadJugadores extends Flujo{
	
	public FlujoIngresarCantidadJugadores(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}

	public void mostarMenuTextual() {
		vista.mostrarMensaje("¿Cuántas personas van a jugar (entre 2 y 4)?");
	}

	public Flujo elegirOpcion(String opcion) {
		try {
			int cantidadJugadores = Integer.parseInt(opcion);
			while(cantidadJugadores < 2 || cantidadJugadores > 4) {
				vista.mostrarMensaje("***¡Debe ingresar un número valido entre 2 y 4!***");
				return this;
			}
			return new FlujoIngresarJugadores(vista,controlador,cantidadJugadores);
		} catch(NumberFormatException e) {
			vista.mostrarMensaje("***¡Debe ingresar un número valido entre 2 y 4!***");
			return this;
		}
	}

}
