package controlador.scrabble;

import java.util.List;

import modelo.scrabble.*;
import modelo.scrabble.Letra;
import modelo.scrabble.ModeloJuego;
import vista.scrabble.Vista;
import vista.scrabble.VistaConsola;

public class Controlador {
	
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
	
	private void agregarJugadores(String jugador1, String jugador2) {
		modelo.addJugadores(jugador1,jugador2);
	}
	
	public void agregarPalabra(int idJugador, int x, int y, String cadenaString, boolean horizontal) {
		
		//Creo la nueva palabra dentro del Controlador
		char[] caracteres = cadenaString.toCharArray();
		Palabra nuevaPalabra = new Palabra(caracteres);
		
		//La envio al modelo
		modelo.addPalabra(idJugador, x, y, nuevaPalabra, horizontal);
		
	}
	
	public Object[][] obtenerTablero() {
		return modelo.getTablero();
	}
	
	public List<Character> obtenerAtril(){
		return modelo.getJugador().getAtril();
	}
	
	public int obtenerPuntaje(){
		return modelo.getJugador().getPuntaje();
	}
	
	
	
	
	
	
	
	

}
