package vista.scrabble.consolagrafica;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;

public class FlujoIngresarJugadores extends Flujo{
	
	private String[] jugadores = new String[4];
	private int cantidadJugadores;
	
	private EstadosPosibles estadoActual = EstadosPosibles.INGRESANDO_NOMBRE_JUGADOR_1;

	public FlujoIngresarJugadores(ConsolaGrafica vista, Controlador controlador, int cantidadJugadores) {
		super(vista, controlador);
		this.cantidadJugadores = cantidadJugadores;
	}
	
	public enum EstadosPosibles{
		INGRESANDO_NOMBRE_JUGADOR_1,
		INGRESANDO_NOMBRE_JUGADOR_2,
		INGRESANDO_NOMBRE_JUGADOR_3,
		INGRESANDO_NOMBRE_JUGADOR_4,
	}

	public void mostarMenuTextual() {
		vista.limpiar();
		switch(estadoActual) {
		case INGRESANDO_NOMBRE_JUGADOR_1 -> vista.mostrarMensaje("¿Cómo se llamará el jugador 1?:");
		case INGRESANDO_NOMBRE_JUGADOR_2 -> vista.mostrarMensaje("¿Cómo se llamará el jugador 2?:");
		case INGRESANDO_NOMBRE_JUGADOR_3 -> vista.mostrarMensaje("¿Cómo se llamará el jugador 3?:");
		case INGRESANDO_NOMBRE_JUGADOR_4 -> vista.mostrarMensaje("¿Cómo se llamará el jugador 4?:");
		}
	}
	
	public Flujo elegirOpcion(String opcion) {
		
		switch(estadoActual) {
			case INGRESANDO_NOMBRE_JUGADOR_1 -> ingresarNombreJugador1(opcion);
			case INGRESANDO_NOMBRE_JUGADOR_2 -> {
				return ingresarNombreJugador2(opcion);
			}
			case INGRESANDO_NOMBRE_JUGADOR_3 -> {
				return ingresarNombreJugador3(opcion);
			}
			case INGRESANDO_NOMBRE_JUGADOR_4 -> {
				return ingresarNombreJugador4(opcion);
			}
		}
		return this;
	}
	
	//INTERFAZ
	public void ingresarNombreJugador1(String jugador1) {
		jugadores[0] = jugador1.toUpperCase();
		estadoActual = EstadosPosibles.INGRESANDO_NOMBRE_JUGADOR_2;
	}
	
	public Flujo ingresarNombreJugador2(String jugador2) {
		jugadores[1] = jugador2.toUpperCase();
		if(cantidadJugadores > 2) {
			estadoActual = EstadosPosibles.INGRESANDO_NOMBRE_JUGADOR_3;
			}
		else {
			try {
				return ingresarJugadores();
			} catch (RemoteException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			}
		return this;
	}
	
	public Flujo ingresarNombreJugador3(String jugador3) {
		jugadores[2] = jugador3.toUpperCase();
		if(cantidadJugadores > 3) {
			estadoActual = EstadosPosibles.INGRESANDO_NOMBRE_JUGADOR_4;
			}
		else {
			try {
				return ingresarJugadores();
			} catch (RemoteException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			}
		return this;
	}
	
	public Flujo ingresarNombreJugador4(String jugador4) {
		jugadores[3] = jugador4.toUpperCase();
		try {
			return ingresarJugadores();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			return this;
		}
	}
	
	public Flujo ingresarJugadores() throws RemoteException {
		Jugador[] jugadores = new Jugador[cantidadJugadores];
		for(int j = 0; j < cantidadJugadores; j++) {
			jugadores[j] = new Jugador(this.jugadores[j]);
		}
		controlador.comenzarPartida(jugadores);
		return new FlujoOpcionesJuego(vista,controlador,controlador.siguienteTurno());
	}
	
	
	

}
	