package flujos.scrabble;

import controlador.scrabble.Controlador;
import flujos.scrabble.FlujoPartida.EstadosPosibles;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.ConsolaGrafica;

public abstract class FlujoCambiarFichas extends Flujo{

	public FlujoCambiarFichas(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador);
		this.idJugador = idJugador;
	}
	
	protected int idJugador;
	
    public void mostarMenuTextual() {
    	vista.mostrarTablero(controlador.obtenerTablero());
		vista.mostrarMensaje("CANT FICHAS: " + controlador.obtenerCantidadFichas());
		vista.mostrarMensaje(mostrarEstadoJugador());
    	vista.mostrarMensaje(controlador.obtenerJugadores(idJugador).getAtril().toString());
        vista.mostrarMensaje("Ingrese una palabra que contenga las letras que desea cambiar (en cualquier orden): ");
    }

    public Flujo elegirOpcion(String opcion) {
    	
    	Jugador jugadorActual = controlador.obtenerJugadores(idJugador);
    	opcion = opcion.toUpperCase();
		char[] cadenaCaracteres = opcion.toCharArray();
		for(char c: cadenaCaracteres) {
			if(!jugadorActual.getAtril().contains(c)) {
				vista.mostrarMensaje("Ingrese una palabra que contenga las letras de su atril.");
				return this;
			}
		}
		controlador.cambiarFichas(idJugador, cadenaCaracteres);
		return avanzarFlujo();
	}
    
    public abstract Flujo avanzarFlujo();
    public abstract String mostrarEstadoJugador();
	
	
}
