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
	
	public void comenzarPartida(int cantidadJugadores) {
		modelo.nuevaPartida(cantidadJugadores);
	}
	
	public void agregarJugador(String nombreJugador) {
		modelo.addJugador(nombreJugador);
	}
	
	public void agregarPalabra(int x, int y, String cadenaString) {
		
		//Creo la nueva palabra dentro del Controlador
		char[] caracteres = cadenaString.toCharArray();
		Palabra nuevaPalabra = new Palabra(caracteres);
		
		//La envio al modelo
		modelo.addPalabra(0, x, y, nuevaPalabra);
		
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
