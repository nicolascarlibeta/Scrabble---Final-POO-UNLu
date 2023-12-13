package vista.scrabble.consolagrafica;

import java.io.IOException;
import java.rmi.RemoteException;

import controlador.scrabble.Controlador;
import modelo.scrabble.Ficha;
import modelo.scrabble.PremioLetra;

public class FlujoOpcionesJuego extends Flujo{
	
	public FlujoOpcionesJuego(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
    public void mostarMenuTextual() {
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
            	return ingresarPalabra();
            }
            case "2" -> {
            	return cambiarFichas();
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
	
    public Flujo ingresarPalabra() {
    	return new FlujoIngresarPalabra(vista,controlador);
    }
    
    public Flujo cambiarFichas() {
    	return new FlujoCambiarFichas(vista,controlador);
    }
    
    public Flujo avanzarFlujo() {
    	controlador.pasarTurno();
    	return new FlujoOpcionesJuego(vista,controlador);
    }
    
    public Flujo volverMenuPrincipal() {
    	try {
    		controlador.guardarPartida();
    		return new FlujoMenuPrincipal(vista,controlador);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			return null;
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			return this;
		}
    }
	
}
