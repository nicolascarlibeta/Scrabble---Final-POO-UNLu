package vista.scrabble.consolagrafica;

import java.io.IOException;
import java.rmi.RemoteException;

import controlador.scrabble.Controlador;
import modelo.scrabble.Diccionario;
import modelo.scrabble.IJugador;
import modelo.scrabble.Casillero;
import modelo.scrabble.PremioLetra;

public class FlujoIngresarPalabra extends Flujo{
	
	public FlujoIngresarPalabra(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	private String cadenaString = "";
	private String x = "";
	private String y = "";
	private EstadosPosibles estadoActual = EstadosPosibles.INGRESANDO_PALABRA;
	
	public enum EstadosPosibles{
		INGRESANDO_PALABRA,
		INGRESANDO_COORDENADA_X,
		INGRESANDO_COORDENADA_Y,
		INGRESANDO_DISPOSICION
	}

	public void mostarMenuTextual() {
		switch(estadoActual) {
		case INGRESANDO_PALABRA -> vista.mostrarMensaje("Ingrese una palabra:");
		case INGRESANDO_COORDENADA_X -> vista.mostrarMensaje("Ingrese la coordenada HORIZONTAL (A, B, C, D, E, F, ...):");
		case INGRESANDO_COORDENADA_Y -> vista.mostrarMensaje("Ingrese la coordenada VERTICAL (A, B, C, D, E, F, ...): ");
		case INGRESANDO_DISPOSICION -> {
			vista.mostrarMensaje("¿De que forma quiere agregar esta palabra?:");
			vista.mostrarMensaje("1. Horizontal\t2. Vertical:");
			}
		}
	}

	public Flujo elegirOpcion(String opcion) {

		switch(estadoActual) {
			case INGRESANDO_PALABRA -> {
				try {
					return ingresarPalabra(opcion);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			case INGRESANDO_COORDENADA_X -> {
				return ingresarX(opcion);
			}
			case INGRESANDO_COORDENADA_Y -> {
				return ingresarY(opcion);
			}
			case INGRESANDO_DISPOSICION ->{
				return ingresarDisposicion(opcion);
			}
	}
			return this;
	}
	
	//INTERFAZ
	
	public Flujo ingresarPalabra(String cadenaString) throws IOException {
		this.cadenaString = cadenaString.toUpperCase();
		if(!controlador.validarFlujo()) {
			estadoActual = EstadosPosibles.INGRESANDO_DISPOSICION;			
		}
		else {
			estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_X;			
		}
		return this;
	}
	
	public Flujo ingresarX(String x) {
		this.x = x;
		estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_Y;
		return this;
	}
	
	public Flujo ingresarY(String y) {
		this.y = y;
		estadoActual = EstadosPosibles.INGRESANDO_DISPOSICION;
		return this;
	}
	
	public Flujo ingresarDisposicion(String disposicion) {
		if(!controlador.agregarPalabra(x,y,cadenaString,disposicion)) {
			return new FlujoIngresarPalabra(vista,controlador);
		}
		return new FlujoOpcionesJuego(vista,controlador);
	}
	
	
	

}
