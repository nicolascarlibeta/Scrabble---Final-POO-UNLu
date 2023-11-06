package ar.edu.unlu.poo.scrabble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ar.edu.unlu.poo.scrabble.Ficha;

public class Scrabble {
	
	private Ficha[][] tablero = new Ficha[6][6];
	private final HashMap<Letra, Integer> fichas = new HashMap<>();
	private Jugador[] jugadores = new Jugador[4];
	private int puntajePalabra;
	
	//CONSTRUCTOR
	
	public Scrabble() {
		cargarTablero();
		cargarFichas();
	}
	
	private void cargarTablero() {
		Ficha casilleroVacio = new Letra("_",1);
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				tablero[f][c] = casilleroVacio;	 		
				}
			}
		//Cargar LETRAS (Borde IZQUIERDO)
		int l = 65;
		for(int f = 1; f < tablero.length; f++) {
			tablero[f][0] = new Letra("" + (char)((char)l),1);
			l++;
			}  
		//Cargar NÚMEROS (Borde DERECHO)
		for(int c = 1; c < tablero[0].length; c++) {
			tablero[0][c] = new Letra("" + c,1);
			}
		tablero[2][1] = new PremioLetra(new PuntajeDoble());
	}
	
	private void cargarFichas() {
		fichas.put(new Letra("A",1), 12);
		fichas.put(new Letra("B",3), 2);
		fichas.put(new Letra("C",3), 4);
		fichas.put(new Letra("CH",5), 1);
		fichas.put(new Letra("D",2), 5);
		fichas.put(new Letra("E",1), 12);
		fichas.put(new Letra("F",4), 1);
		fichas.put(new Letra("G",2), 2);
		fichas.put(new Letra("H",4), 2);
		fichas.put(new Letra("I",1), 6);
		fichas.put(new Letra("J",8), 1);
		fichas.put(new Letra("L",1), 4);
		fichas.put(new Letra("LL",8), 1);
		fichas.put(new Letra("M",3), 2);
		fichas.put(new Letra("N",1), 5);
		fichas.put(new Letra("Ñ",8), 1);
		fichas.put(new Letra("O",1), 9);
		fichas.put(new Letra("P",3), 2);
		fichas.put(new Letra("Q",5), 1);
		fichas.put(new Letra("R",1), 5);
		fichas.put(new Letra("RR",8), 1);
		fichas.put(new Letra("S",1), 6);
		fichas.put(new Letra("T",1), 4);
		fichas.put(new Letra("U",1), 5);
		fichas.put(new Letra("V",4), 1);
		fichas.put(new Letra("X",8), 1);
		fichas.put(new Letra("Y",4), 1);
		fichas.put(new Letra("Z",10), 1);
		fichas.put(new Letra(" ",0), 2);
	}
	
	//INTERFAZ
	
	public void addLetra(int x, int y, Letra letra) {
		
		Ficha fichaActual = tablero[x][y];
		fichas.put(letra, fichas.get(letra) - 1);
		
		//Primero, me fijo si es un premio de Letra
		puntajePalabra += letra.getPuntos() * fichaActual.getPremioLetra();
		
		//Una vez que se envia la palabra, me fijo
		//si tiene un premio de Palabra
		
		//puntajePalabra *= fichaActual.getPremioPalabra();
		
		//Coloco la ficha
		
		tablero[x][y] = letra;
	}
	
	public Ficha[][] getTablero() {
		return tablero;
	}
	
	public HashMap<Letra, Integer> getFichas() {
		return fichas;
	}
	
	public int getPuntajePalabra() {
		return puntajePalabra;
	}
	
	public String toString(){
		String dato = "";
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				dato += tablero[f][c].getLetra() + " ";			
			}
			dato += "\n"; 
		}
		return dato;
	}
	
	
	
	

}
