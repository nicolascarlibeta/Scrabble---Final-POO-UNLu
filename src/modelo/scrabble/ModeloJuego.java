package modelo.scrabble;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import modelo.scrabble.Ficha;
import obs.scrabble.Observado;
import obs.scrabble.Observador;

public class ModeloJuego implements Observado{
	
	 // * TABLERO: 
	private Ficha[][] tablero = new Ficha[16][16];
	// * FICHAS:
	private PuntajeFichas fichas = new PuntajeFichas();
	// * BOLSA DE FICHAS: 
	private HashMap<Character, Integer> bolsaDeFichas = new HashMap<>();
	// * CANTIDAD DE FICHAS ACTUAL:
	private int cantidadFichasBolsa = 100;
	// * CANTIDAD DE FICHAS A REPARTIR:
	private final int cantidadFichas = 7;
	// * JUGADORES:
	private Jugador[] jugadores = new Jugador[2];
	// * CANTIDAD DE JUGADORES:
	private final int cantidadJugadores = 2;
	// * OBSERVADORES:
	private ArrayList<Observador> observadores = new ArrayList<>();
	
	
	//CONSTRUCTOR
	
	public ModeloJuego() {
		cargarTablero();
		cargarFichas();
	}
	
	private void cargarTablero() {
		Ficha casilleroVacio = new Letra("_");
		tablero[0][0] = new Letra(" ");
		for(int f = 1; f < tablero.length; f++) {
			for(int c = 1; c < tablero[f].length; c++) {
				tablero[f][c] = casilleroVacio;	 		
				}
			}
		//Cargar LETRAS (Borde IZQUIERDO y DERECHO)
		int l = 65;
		for(int f = 1; f < tablero.length; f++) {
			tablero[f][0] = new Letra("" + ((char)l));
			tablero[0][f] = new Letra("" + ((char)l));
			l++;
		}  
	
		cargarPremios();
		
	}
	
	private void cargarPremios() {
		
	//Premio PALABRA DOBLE
	for(int pd = 1; pd <= tablero.length - 1; pd ++) {
		tablero[pd][pd] = new PremioPalabra(TipoPuntaje.DOBLE);					
		}
	for(int pd = 1; pd <= tablero.length - 1; pd ++) {
		tablero[pd][(tablero.length - 1) - (pd - 1)] = new PremioPalabra(TipoPuntaje.DOBLE);
		}
	
	//Premio LETRA TRIPLE
	for(int x = 2; x <= tablero.length - 1; x += 12) {
		for(int y = 6; y <= 10; y += 4) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.TRIPLE);										
			}
		}
	for(int x = 6; x <= 10; x += 4) {
		for(int y = 2; y <= tablero.length - 1; y += 4) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.TRIPLE);										
			}
		}
	
	//Premio PALABRA TRIPLE
	for(int x = 1; x <= tablero.length - 1; x += 7) {
		for(int y = 1; y <= tablero.length - 1; y += 7) {
			tablero[x][y] = new PremioPalabra(TipoPuntaje.TRIPLE);					
			}
		}	
	
	//Premio LETRA DOBLE (Alrededores)
	for(int x = 4; x <= tablero.length - 1; x += 8) {
		for(int y = 1; y <= tablero.length - 1; y += 14) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.DOBLE);					
			}
		}
	for(int x = 1; x <= tablero.length - 1; x += 14) {
		for(int y = 4; y <= tablero.length - 1; y += 8) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.DOBLE);					
			}
		}
	
	//Premio LETRA DOBLE (Centrales) 
	for(int x = 3; x <= tablero.length - 1; x += 10) {
		for(int y = 7; y <= 9; y += 2) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.DOBLE);					
			}
		}
	for(int x = 7; x <= 9; x += 2) {
		for(int y = 7; y <= 9; y += 2) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.DOBLE);					
			}
		}
	for(int x = 7; x <= 9; x += 2) {
		for(int y = 3; y <= tablero.length - 1; y += 10) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.DOBLE);					
			}
		}
	for(int y = 8; y <= 8; y++) {
		for(int x = 4; x <= tablero.length - 1; x += 8) {
			tablero[x][y] = new PremioLetra(TipoPuntaje.DOBLE);
			tablero[y][x] = new PremioLetra(TipoPuntaje.DOBLE);
			}
		}
	
	//Premio PALABRA DOBLE (Estrella)
	tablero[8][8] = new PremioPalabra(TipoPuntaje.DOBLE);
	
	}
	
	private void cargarFichas() {
		
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
	
	public void cargarPartida() {
		
		//Genero el grupo de las vocales (al menos 4 vocales en el atril)
		char vocales[] = {'A','E','I','O','U'};
		
		//Le repartimos aleatoriamente las 7 fichas a cada jugador
		for(int j = 0; j < cantidadJugadores; j++) {
			for(int f = 0; f < cantidadFichas - 4; f++) {
				//Reparto las 7 fichas iniciales (tres primero)

				char letra = (char)((char)new Random().nextInt(91 - 65) + 65);
					
				while(bolsaDeFichas.get(letra) == null || bolsaDeFichas.get(letra) == 0) {
					letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				}
				repartirFichas(j, letra);				
			}
		}
		for(int j = 0; j < cantidadJugadores; j++) {
			for(int f = 0; f < 4; f++) {
				//Reparto las 7 fichas iniciales
				int random = new Random().nextInt(vocales.length);
				repartirFichas(j, vocales[random]);				
			}
		}
	}
	
	public void repartirFichas(int idJugador, char letra) {
		
		//Le agrego las fichas al atril del jugador
		jugadores[idJugador].getAtril().add(letra);

		//Le quito las fichas a la bolsa
		bolsaDeFichas.put(letra, bolsaDeFichas.get(letra) - 1);
		cantidadFichasBolsa--;	
	}
	
	public void devolverFichas(int idJugador, char[] fichasACambiar) {
		
		for(Character f: fichasACambiar) {
			
			//Elimino la ficha del atril
			jugadores[idJugador].getAtril().remove(f);
			
			//Devuelvo la ficha del atril a la bolsa
			bolsaDeFichas.put(f, bolsaDeFichas.get(f) + 1);
		}
		
		//Le reparto las fichas restantes al jugador
		List<Character> atril = jugadores[idJugador].getAtril();
		if(cantidadFichasBolsa > 0) {
			int cantidadARepartir = cantidadFichas - atril.size();
			for(int c = 0; c < cantidadARepartir; c++) {
				char letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				
				while(bolsaDeFichas.get(letra) == null || bolsaDeFichas.get(letra) == 0) {
					letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				}
				repartirFichas(idJugador,letra);
			}
			cantidadFichasBolsa += cantidadARepartir;
		}
	} 
	
	public void addJugadores(String jugador1, String jugador2) {
		jugadores[0] = new Jugador(jugador1);
		jugadores[1] = new Jugador(jugador2);
	}
	
	public void addPalabra(int idJugador, int x, int y, Palabra palabraActual, boolean horizontal) {
		
		//Hago un alias del conjunto de letras de la palabra
		char[] letrasPalabra = palabraActual.getLetras();
		//length de la palabra
		//Hago un alias del atril del jugador
		List<Character> atril = jugadores[idJugador].getAtril();
		
		//Hago un formateo de x e y
		x -= 64; y -=64;
		
		//Calculo el puntaje inicial de la palabra
		int puntajePalabra = calcularPuntajePalabra(x, y, letrasPalabra, horizontal);
		
		//Seteo el puntaje al jugador
		jugadores[idJugador].setPuntaje(jugadores[idJugador].getPuntaje() + puntajePalabra);
				
		//Coloco las letras en el tablero y se las resto del atril al jugador
		int i = x, d = y;
		for(Character l: letrasPalabra) {
			tablero[i][d] = new Letra(l + "");
			atril.remove(l);
			if(horizontal) {
				d++;				
			}
			else{
				i++;
			}
		}
		
		//Le reparto las fichas restantes al jugador
		if(cantidadFichasBolsa > 0) {
			int cantidadARepartir = cantidadFichas - atril.size();
			for(int c = 0; c < cantidadARepartir; c++) {
				char letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				
				while(bolsaDeFichas.get(letra) == null || bolsaDeFichas.get(letra) == 0) {
					letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				}
				repartirFichas(idJugador,letra);
			}
		}
	}
		
	private int calcularPuntajePalabra(int x, int y, char[] letrasPalabra, boolean horizontal) {
		
		//Calculo el puntaje inicial
		int puntajePalabra = 0;
		int cantVecesMultiplicar = 1;
		boolean multiplicarPalabra = false;
		
		int i = x, d = y;
	
		for(char letra: letrasPalabra) {
			
			if(tablero[i][d].getClass() == PremioLetra.class) {
				puntajePalabra += fichas.getPuntaje(letra + "") * tablero[i][d].getPuntos();					
			}
			else if(tablero[i][d].getClass() == PremioPalabra.class) {
				puntajePalabra += fichas.getPuntaje(letra + "");
				cantVecesMultiplicar *= tablero[i][d].getPuntos();
				multiplicarPalabra = true;
			}
			else {
				puntajePalabra += fichas.getPuntaje(letra + "");
			}
			
			if(horizontal) {
				d++;				
			}
			else{
				i++;
			}
		}
		
		if(multiplicarPalabra) {
			puntajePalabra *= cantVecesMultiplicar;
		}
		return puntajePalabra;
	}
	
	public int getGanador() {
		int ganador;
		if(jugadores[0].getPuntaje() > jugadores[1].getPuntaje()) {
			ganador = 0;
		}
		else {
			ganador = 1;
		}
		return ganador;
	}
	
	public int getCantidadJugadores() {
		return cantidadJugadores;
	}
	
	public Ficha[][] getTablero() {
		return tablero;
	}

	public HashMap<Character, Integer> getBolsaDeFichas() {
		return bolsaDeFichas;
	}
	
	public Jugador[] getJugadores() {
		return jugadores;
	}
	
	public boolean isVacia() {
		return cantidadFichasBolsa == 0;
	}
	
	public int getCantidadFichasBolsa() {
		return cantidadFichasBolsa;
	}
	
	public boolean isPrimerMovimiento() {
		return jugadores[0].getPuntaje() == 0 && jugadores[1].getPuntaje() == 0;
	}
	
	public void ligar(Observador o) {
		observadores.add(o);
	}

	public void desligar(Observador o) {
		observadores.remove(o);
	}

	public void notificar(Evento evento) {
		for(Observador o: observadores) {
			o.actualizar(evento);
		}
	}

}
