package vista.scrabble.consolagrafica;

import controlador.scrabble.Controlador;

public class FlujoIngresarJugadores extends Flujo{
	
	private String jugador1 = "", jugador2 = "";
	private EstadosPosibles estadoActual = EstadosPosibles.INGRESANDO_NOMBRE_JUGADOR_1;

	public FlujoIngresarJugadores(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	public enum EstadosPosibles{
		INGRESANDO_NOMBRE_JUGADOR_1,
		INGRESANDO_NOMBRE_JUGADOR_2,
	}

	public void mostarMenuTextual() {
		
		switch(estadoActual) {
		case INGRESANDO_NOMBRE_JUGADOR_1 -> vista.println("¿Cómo se llamará tu jugador?:");
		case INGRESANDO_NOMBRE_JUGADOR_2 -> vista.println("¿Cómo se llamará el jugador oponente?:");
		}
	}
	
	public Flujo elegirOpcion(String opcion) {
		
		switch(estadoActual) {
			case INGRESANDO_NOMBRE_JUGADOR_1 -> ingresarNombreJugador1(opcion);
			case INGRESANDO_NOMBRE_JUGADOR_2 -> {
				return ingresarNombreJugador2(opcion);
			}
		}
		return this;
	}
	
	//INTERFAZ
	public void ingresarNombreJugador1(String jugador1) {
		this.jugador1 = jugador1;
		estadoActual = EstadosPosibles.INGRESANDO_NOMBRE_JUGADOR_2;
	}
	
	public Flujo ingresarNombreJugador2(String jugador2) {
		this.jugador2 = jugador2;
		controlador.comenzarPartida(jugador1,jugador2);
		return new FlujoComenzarPartida(vista,controlador);
	}

}
	