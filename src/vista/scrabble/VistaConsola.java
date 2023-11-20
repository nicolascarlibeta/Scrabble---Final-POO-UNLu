package vista.scrabble;

import java.util.List;
import java.util.Scanner;

import controlador.scrabble.Controlador;

public class VistaConsola implements Vista{
/*
	private Controlador controlador;
	
	public VistaConsola(Controlador controlador) {
		this.controlador = controlador;
		controlador.setVista(this);
	}
	
	public Scanner scanner() {
		return new Scanner(System.in);
	}
	
	//Comenzar Partida (Método Principal)
	public void mostrarComenzarPartida(){
		
		mostrarMensaje("Ingrese la cantidad de jugadores: ");
		int cantidadJugadores = scanner().nextInt();
		
		//controlador.comenzarPartida(cantidadJugadores);
		
		// * CICLO DEL JUEGO
		while(true) {
			for(int r = 0; r < cantidadJugadores; r++) {
				mostrarTableroJuego();
				mostrarMensaje("\n");
				mostrarMensaje(" ");
				mostrarAtril();
				mostrarPuntaje();
				mostrarMensaje("\n");
				mostrarMensaje(" ");
				mostrarAgregarPalabra();
			}
		}
		
	}
	
	public void mostrarAgregarJugadores() {
		
		mostrarMensaje("Ingrese el nombre del jugador: ");
		String nombreJugador = scanner().nextLine();
		controlador.agregarJugador(nombreJugador);
		
	}
	
	public void mostrarAgregarPalabra() {
		
		mostrarMensaje("Ingrese una palabra: ");
		String cadenaString = scanner().nextLine();
		mostrarMensaje("Ingrese la coordenada x: ");
		int x = scanner().nextInt();
		mostrarMensaje("Ingrese la coordenada y: ");
		int y = scanner().nextInt();
		controlador.agregarPalabra(x,y,cadenaString);
		
	}
	
	public void mostrarTableroJuego() {
		
		Object[][] tablero = controlador.obtenerTablero();
		String mostrarTablero = "";
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				mostrarTablero += tablero[f][c] + " ";			
			}
			mostrarTablero += "\n"; 
		}
		mostrarMensaje(mostrarTablero);
		
	}
	
	public void mostrarMensaje(String mensaje) {
		System.out.print(mensaje);
	}
	
	public void mostrarAtril() {
		
		List<Character> atril = controlador.obtenerAtril();
		String mostrarAtril = "";
		for(int f = 0; f < atril.size(); f++) {
			mostrarAtril += atril.get(f) + " ";
		}
		mostrarAtril += "\n";
		mostrarMensaje(mostrarAtril);
		
	}
	
	public void mostrarPuntaje() {
		
		mostrarMensaje("Puntaje: " + controlador.obtenerPuntaje());
		
	}
*/


	
	
}
