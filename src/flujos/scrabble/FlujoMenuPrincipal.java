package flujos.scrabble;

import controlador.scrabble.*;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoMenuPrincipal extends Flujo{

	public FlujoMenuPrincipal(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	//Cáscara del menú
    public void mostarMenuTextual() {
		vista.mostrarMensaje("SCRABBLE");
        vista.mostrarMensaje("Menú Principal:");
        vista.mostrarMensaje("1. Comenzar partida.");
        vista.mostrarMensaje("2. Reglas de juego.");
        vista.mostrarMensaje("3. Salir.");
        vista.mostrarMensaje("Seleccione una opción: ");
    }

	//Menú
    public Flujo elegirOpcion(String opcion) {
        switch (opcion) {
            case "1" -> {
            	return new FlujoIngresarJugadores(vista, controlador);
            }
            default -> vista.mostrarMensaje("Opción inválida");
        }
        return this;
    }
    
    

	
	
	

}
