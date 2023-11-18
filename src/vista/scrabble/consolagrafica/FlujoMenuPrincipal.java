package vista.scrabble.consolagrafica;

import controlador.scrabble.*;

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
            	controlador.comenzarPartida(1);
            	return new FlujoComenzarPartida(vista, controlador);
            }
            default -> vista.println("Opción inválida");
        }
        return this;
    }
    
public String mostrarTableroJuego() {
		
		Object[][] tablero = controlador.obtenerTablero();
		String mostrarTablero = "";
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				mostrarTablero += tablero[f][c] + " ";			
			}
			mostrarTablero += "\n"; 
		}
		return mostrarTablero;
	}

    

	
	
	

}
