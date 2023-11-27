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
		modelo.ligar(this);
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
	
	public Ficha[][] obtenerTablero(){
		return modelo.getTablero();
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
	
	public void actualizar(Evento evento) {
		switch (evento) {
		case NUEVOS_JUGADORES -> {
			Jugador[] nuevosJugadores = modelo.getJugadores();
			vista.mostrarIngresarJugadores(nuevosJugadores);				
			}
		case NUEVA_PARTIDA -> {
			Jugador[] jugadores = modelo.getJugadores();
			vista.mostrarComenzarPartida(jugadores);				
			}
		case NUEVA_PALABRA -> {
			vista.mostrarMensaje("Se ha agregado la palabra correctamente.");				
			}
		case CAMBIO_FICHAS -> {
			vista.mostrarMensaje("Se han cambiado las fichas correctamente.");				
			}
		case CAMBIO_TABLERO -> {
			Ficha[][] tablero = modelo.getTablero();
			vista.mostrarTablero(tablero);				
			}
		}
	}

	

	
	

}
