package modelo.scrabble;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import modelo.scrabble.Ficha;

public class ModeloJuego {
	
	 // * TABLERO: 
	private Object[][] tablero = new Object[6][6];
	// * FICHAS:
	private final HashMap<Character, Integer> fichas = new HashMap<>();
	// * BOLSA DE FICHAS: 
	private HashMap<Character, Integer> bolsaDeFichas = new HashMap<>();
	// * CANTIDAD DE FICHAS A REPARTIR:
	private final int cantidadFichas = 7;
	// * JUGADORES:
	private Jugador[] jugadores = new Jugador[4];
	
	
	
	//CONSTRUCTOR
	
	public ModeloJuego() {
		cargarTablero();
		cargarFichas();
	}
	
	private void cargarTablero() {
		Character casilleroVacio = '.';
		tablero[0][0] = ' ';
		for(int f = 1; f < tablero.length; f++) {
			for(int c = 1; c < tablero[f].length; c++) {
				tablero[f][c] = casilleroVacio;	 		
				}
			}
		//Cargar LETRAS (Borde IZQUIERDO)
		int l = 65;
		for(int f = 1; f < tablero.length; f++) {
			tablero[f][0] = (char)((char)l);
			l++;
		}  
		//Cargar NÚMEROS (Borde DERECHO)
		for(int c = 1; c < tablero[0].length; c++) {
			tablero[0][c] = "" + c;
		}
		//tablero[2][1] = new PremioLetra(new PuntajeDoble());
	}
	
	private void cargarFichas() {
		
		//FICHAS.
		fichas.put('A', 1); fichas.put('B', 3); fichas.put('C', 3); fichas.put('D', 2); fichas.put('E', 1); 
		fichas.put('F', 4); fichas.put('G', 2);fichas.put('H', 4); fichas.put('I', 1); fichas.put('J', 8); fichas.put('L', 1);
		fichas.put('M', 3); fichas.put('N', 1); fichas.put('Ñ', 8); fichas.put('O', 1); fichas.put('P', 3); fichas.put('Q', 5);
		fichas.put('R', 1); fichas.put('S', 1); fichas.put('T', 1); fichas.put('U', 1);
		fichas.put('V', 4); fichas.put('X', 8); fichas.put('Y', 4); fichas.put('Z', 10);
		fichas.put(' ', 0);
		
		//BOLSA DE FICHAS.
		bolsaDeFichas.put('A', 12); bolsaDeFichas.put('B', 2); bolsaDeFichas.put('C', 4); 
		bolsaDeFichas.put('D', 5); bolsaDeFichas.put('E', 12); bolsaDeFichas.put('F', 1); bolsaDeFichas.put('G', 2);
		bolsaDeFichas.put('H', 2); bolsaDeFichas.put('I', 6); bolsaDeFichas.put('J', 1); bolsaDeFichas.put('L', 4);
		bolsaDeFichas.put('M', 2); bolsaDeFichas.put('N', 5); bolsaDeFichas.put('Ñ', 1);
		bolsaDeFichas.put('O', 9); bolsaDeFichas.put('P', 2); bolsaDeFichas.put('Q', 1); bolsaDeFichas.put('R', 5);
		bolsaDeFichas.put('S', 6); bolsaDeFichas.put('T', 4); bolsaDeFichas.put('U', 5);
		bolsaDeFichas.put('V', 1); bolsaDeFichas.put('X', 1); bolsaDeFichas.put('Y', 1); bolsaDeFichas.put('Z', 1);
		bolsaDeFichas.put(' ', 2);
	
	}
	
	//INTERFAZ
	
	public void nuevaPartida(int cantidadJugadores) {
		
		//Creamos y agregamos la cantidad de jugadores de la partida
		for(int j = 0; j < cantidadJugadores; j++) {
			addJugador("Javier Carli");
		}
		
		//Le repartimos aleatoriamente las 7 fichas a cada jugador
		for(int f = 0; f < cantidadFichas; f++) {
			char letra = (char)((char)new Random().nextInt(91 - 65) + 65);
			jugadores[0].getAtril().add(letra);
			//bolsaDeFichas
		}
		
	}
	
	public void addJugador(String nombreJugador) { 
		Jugador nuevoJugador = new Jugador(nombreJugador);
		jugadores[0] = nuevoJugador;
	}
	
	public void addPalabra(int idJugador, int x, int y, Palabra palabraActual) {
		
		//Hago un alias de la posición del tablero
		Object casilleroActual = tablero[x][y];
		
		//Hago un alias del conjunto de letras de la palabra
		char[] letrasPalabra = palabraActual.getLetras();
		
		//Hago un alias del atril del jugador
		List<Character> atril = jugadores[idJugador].getAtril();
		
		//Calcular puntaje
		int puntajePalabra = 0; //Puntaje provisorio
		for(Character l: letrasPalabra) {
			puntajePalabra += fichas.get(l);
		}
		//Seteo el puntaje a la palabra
		palabraActual.setPuntaje(puntajePalabra);
		//Seteo el puntaje al jugador
		jugadores[idJugador].setPuntaje(jugadores[idJugador].getPuntaje() + puntajePalabra);
		
		//Coloco las letras en el tablero y se las resto al atril del jugador
		int d = y;
		for(Character l: letrasPalabra) {
			tablero[x][d] = l;
			atril.remove(l);
			d++;
		}

	}
	
	public Object[][] getTablero() {
		return tablero;
	}
	
	public HashMap<Character, Integer> getFichas() {
		return fichas;
	}
	
	public HashMap<Character, Integer> getBolsaDeFichas() {
		return bolsaDeFichas;
	}
	
	public Jugador getJugador() {
		return jugadores[0];
	}

}
