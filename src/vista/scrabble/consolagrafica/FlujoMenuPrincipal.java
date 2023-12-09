package vista.scrabble.consolagrafica;

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
        vista.mostrarMensaje("3. Reglas de juego.");
        vista.mostrarMensaje("4. Salir.");
        vista.mostrarMensaje("Seleccione una opción: ");
    }

	//Menú
    public Flujo elegirOpcion(String opcion) {
        switch (opcion) {
            case "1" -> {
            	return new FlujoIngresarCantidadJugadores(vista, controlador);
            }
            case "2" -> {
            	return new FlujoElegirPartida(vista, controlador);
            }
            default -> vista.mostrarMensaje("Opción inválida");
        }
        return this;
    }
    
    

	
	
	

}
