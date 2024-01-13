package vista.scrabble.consolagrafica;

import java.rmi.RemoteException;

import controlador.scrabble.*;

public class FlujoMenuPrincipal extends Flujo{

	
	public FlujoMenuPrincipal(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	//Cáscara del menú
    public void mostarMenuTextual() {
		vista.mostrarMensaje("SCRABBLE");
        vista.mostrarMensaje("Menú Principal:");
        vista.mostrarMensaje("1. Comenzar nueva partida.");
        vista.mostrarMensaje("2. Cargar partida.");
        vista.mostrarMensaje("3. Ver ranking de mejores jugadores.");
        vista.mostrarMensaje("4. Salir.");
        vista.mostrarMensaje("Seleccione una opción: ");
    }

	//Menú
    public Flujo elegirOpcion(String opcion) {
        switch (opcion) {
            case "1" -> {
            	controlador.comenzarPartida();
            	return new FlujoOpcionesJuego(vista, controlador);
            }
            case "2" -> {
            	return new FlujoElegirPartida(vista, controlador);
            }
            case "3" -> {
            	return new FlujoVerRanking(vista, controlador);
            }
            case "4" -> {
            	System.exit(0);
            }
            default -> vista.mostrarMensaje("Opción inválida");
        }
        return this;
    }
    
    

	
	
	

}
