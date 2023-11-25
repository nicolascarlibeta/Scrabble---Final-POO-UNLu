package flujos.scrabble;

import controlador.scrabble.Controlador;
import modelo.scrabble.Ficha;
import modelo.scrabble.PremioLetra;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public abstract class FlujoOpcionesJuego extends Flujo{
	
	public FlujoOpcionesJuego(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
    public void mostarMenuTextual() {
    	vista.println(controlador.obtenerTablero());
		vista.println("CANT. FICHAS: " + controlador.obtenerCantidadFichas());
		vista.println(mostrarEstadoJugador());
        vista.println("¿Que deseas hacer?:");
        vista.println("1. Agregar palabra.");
        vista.println("2. Cambiar las fichas.");
        vista.println("3. Pasar el turno.");
        vista.println("Seleccione una opción: ");
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
            default -> vista.println("Opción inválida");
        }
        return this;
    }
    
    public abstract String mostrarEstadoJugador();

    public abstract Flujo ingresarPalabra();
    
    public abstract Flujo cambiarFichas();
    
    public abstract Flujo avanzarFlujo();
	
}
