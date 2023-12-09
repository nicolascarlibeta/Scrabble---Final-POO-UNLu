package vista.scrabble.consolagrafica;

import java.io.IOException;
import java.rmi.RemoteException;

import controlador.scrabble.Controlador;
import modelo.scrabble.Ficha;
import modelo.scrabble.PremioLetra;

public class FlujoOpcionesJuego extends Flujo{
	
	private int idJugador;
	
	public FlujoOpcionesJuego(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador);
		this.idJugador = idJugador;
	}
	
    public void mostarMenuTextual() {
    	vista.mostrarTablero(controlador.obtenerTablero());	
		vista.mostrarEstadoJugador(controlador.obtenerJugadores(idJugador));	
        vista.mostrarMensaje("¿Que deseas hacer?:");
        vista.mostrarMensaje("1. Agregar palabra.");
        vista.mostrarMensaje("2. Cambiar las fichas.");
        vista.mostrarMensaje("3. Pasar el turno.");
        vista.mostrarMensaje("4. Volver al menú principal.");
        vista.mostrarMensaje("Seleccione una opción: ");
    }

	public Flujo elegirOpcion(String opcion) {
        switch (opcion) {
            case "1" -> {
            	return ingresarPalabra(idJugador);
            }
            case "2" -> {
            	return cambiarFichas(idJugador);
            }
            case "3" -> {
            	return avanzarFlujo();
            }
            case "4" -> {
            	return volverMenuPrincipal();
            }
            default -> vista.mostrarMensaje("Opción inválida");
        }
        return this;
    }

	//INTERFAZ
	
    public Flujo ingresarPalabra(int idJugador) {
    	return new FlujoIngresarPalabra(vista,controlador,idJugador);
    }
    
    public Flujo cambiarFichas(int idJugador) {
    	return new FlujoCambiarFichas(vista,controlador,idJugador);
    }
    
    public Flujo avanzarFlujo() {
    	return new FlujoOpcionesJuego(vista,controlador,controlador.siguienteTurno());
    }
    
    public Flujo volverMenuPrincipal() {
    	try {
    		controlador.guardarPartida();
    		return new FlujoMenuPrincipal(vista,controlador);
			//return new FlujoMenuPrincipal(vista,controlador);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			return null;
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			return this;
		}
    }
	
}
