package controlador.scrabble;

import java.util.List;

import modelo.scrabble.*;
import obs.scrabble.Observador;
import vista.scrabble.Vista;

public class Controlador implements Observador{
	
	private Vista vista;
	private ModeloJuego modelo;
	
	public Controlador(ModeloJuego modelo) {
		this.modelo = modelo;
	}
	
	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
	public void comenzarPartida(String jugador1, String jugador2) {
		agregarJugadores(jugador1,jugador2);
		modelo.cargarPartida();
	}
	
	public void cambiarFichas(int idJugador, char[] cadenaCaracteres) {
		modelo.devolverFichas(idJugador,cadenaCaracteres);
	}
	
	private void agregarJugadores(String jugador1, String jugador2) {
		modelo.addJugadores(jugador1,jugador2);
	}
	
	public void agregarPalabra(int idJugador, int x, int y, String cadenaString, boolean horizontal) {
		
		//Creo la nueva palabra dentro del Controlador
		Palabra nuevaPalabra = new Palabra(cadenaString);
		
		//La envio al modelo
		modelo.addPalabra(idJugador, x, y, nuevaPalabra, horizontal);
		
	}
	
	public String obtenerTablero() {
		Ficha[][] tablero = modelo.getTablero();
		String obtenerTablero = "";
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				obtenerTablero += tablero[f][c].getLetra() + " ";
				if(tablero[f][c].getClass() == PremioLetra.class) {
				}
			}
			obtenerTablero += "\n"; 
		}
		return obtenerTablero;
	}
	
	public int obtenerGanador(){
		return modelo.getGanador();
	}
	
	public int obtenerCantidadJugadores() {
		return modelo.getCantidadJugadores();
	}
	
	public Jugador obtenerJugadores(int idJugador){
		return modelo.getJugadores()[idJugador];
	}
	
	public boolean bolsaEstaVacia() {
		return modelo.isVacia();
	}
	
	public int obtenerCantidadFichas() {
		return modelo.getCantidadFichasBolsa();
	}
	
	public boolean esPrimerMovimiento() {
		return modelo.isPrimerMovimiento();
	}
	
/*
	public void actualizar(Object arg) {
		if (arg instanceof Evento) {
			switch ((Evento) arg) {
			case NUEVA_PALABRA -> {
				//vista.mostrarListaUsuarios((IUsuario[]) this.modelo.getUsuarios());				
			}
				
		}
	}*/

	@Override
	public void actualizar(Object arg) {
		// TODO Apéndice de método generado automáticamente
		
	}
	
	

}
