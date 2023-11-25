package flujos.scrabble;

import controlador.scrabble.*;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public class FlujoMenuPrincipal extends Flujo{

	public FlujoMenuPrincipal(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	//Cáscara del menú
    public void mostarMenuTextual() {
		vista.println("SCRABBLE");
        vista.println("Menú Principal:");
        vista.println("1. Comenzar partida.");
        vista.println("2. Reglas de juego.");
        vista.println("3. Salir.");
        vista.println("Seleccione una opción: ");
    }

	//Menú
    public Flujo elegirOpcion(String opcion) {
        switch (opcion) {
            case "1" -> {
            	return new FlujoIngresarJugadores(vista, controlador);
            }
            default -> vista.println("Opción inválida");
        }
        return this;
    }
    
    

	
	
	

}
