package modelo.scrabble;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

public class Tablero implements Serializable{
	
	// * TABLERO:
	private Ficha[][] tablero = new Ficha[16][16];
	// * CANTIDAD DE FICHAS A REPARTIR:
	private final int cantidadFichas = 7;
	
	//CONSTRUCTOR
	public Tablero() {
		cargarTablero();
		cargarPremios();
	}
	
	//INTERFAZ
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
	
	//Comenzar primera partida
	
	public void comenzarPartida(Jugador[] jugadores, BolsaFichas bolsaDeFichas) throws RemoteException {
		
		//Genero el grupo de las vocales (al menos 4 vocales en el atril)
		char vocales[] = {'A','E','I','O','U'};
		
		//Represento la cantidad actual de jugadores
		int cantidadJugadores = jugadores.length;
		
		//Le repartimos aleatoriamente las 7 fichas a cada jugador
		for(int j = 0; j < cantidadJugadores; j++) {
			for(int f = 0; f < cantidadFichas - 4; f++) {
				
				//Reparto las 7 fichas iniciales (tres primero)
				char letra = (char)((char)new Random().nextInt(91 - 65) + 65);
					
				while(bolsaDeFichas.get(letra) == -1 || bolsaDeFichas.get(letra) == 0) {
					letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				}
				repartirFichas(jugadores, bolsaDeFichas, j, letra);				
			}
		}
		for(int j = 0; j < cantidadJugadores; j++) {
			for(int f = 0; f < 4; f++) {
				//Reparto las 7 fichas iniciales
				int random = new Random().nextInt(vocales.length);
				repartirFichas(jugadores, bolsaDeFichas, j, vocales[random]);				
			}
		}
	}
	
	
	
	//Repartir fichas
	
	public void repartirFichas(Jugador[] jugadores, BolsaFichas bolsaDeFichas, int idJugador, char letra) {
		
		//Le agrego las fichas al atril del jugador
		jugadores[idJugador].getAtril().add(letra);

		//Le quito las fichas a la bolsa
		bolsaDeFichas.put(letra, bolsaDeFichas.get(letra) - 1);
		bolsaDeFichas.setCantidadFichas(bolsaDeFichas.getCantidadFichas() - 1);
	}
	
	
	
	//Devolver fichas
	
	public void devolverFichas(Jugador[] jugadores, BolsaFichas bolsaDeFichas, int idJugador, char[] fichasACambiar) throws RemoteException {
		
		for(Character f: fichasACambiar) {
			
			//Elimino la ficha del atril
			jugadores[idJugador].getAtril().remove(f);
			
			//Devuelvo la ficha del atril a la bolsa
			bolsaDeFichas.put(f, bolsaDeFichas.get(f) + 1);
		}
		
		//Le reparto las fichas restantes al jugador
		List<Character> atril = jugadores[idJugador].getAtril();
		if(bolsaDeFichas.getCantidadFichas() > 0) {
			int cantidadARepartir = cantidadFichas - atril.size();
			for(int c = 0; c < cantidadARepartir; c++) {
				char letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				
				while(bolsaDeFichas.get(letra) == -1 || bolsaDeFichas.get(letra) == 0) {
					letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				}
				repartirFichas(jugadores,bolsaDeFichas,idJugador,letra);
			}
			bolsaDeFichas.setCantidadFichas(bolsaDeFichas.getCantidadFichas() + cantidadARepartir);
		}
	} 
	
	
	
	//Agregar palabra
	
	public void addPalabra(Jugador[] jugadores, BolsaFichas bolsaDeFichas, int idJugador, int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException {
		
		//PROCESAMIENTO:
		
		//Hago un alias del conjunto de letras de la palabra
		char[] letrasPalabra = palabraActual.getLetras();
		//length de la palabra
		//Hago un alias del atril del jugador
		List<Character> atril = jugadores[idJugador].getAtril();
		
		//Hago un formateo de x e y
		x -= 64; y -=64;
		
		
		//VALIDACIÓN:
		
		
		
		
		
		
		
		
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
		if(bolsaDeFichas.getCantidadFichas() > 0) {
			int cantidadARepartir = cantidadFichas - atril.size();
			for(int c = 0; c < cantidadARepartir; c++) {
				char letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				
				while(bolsaDeFichas.get(letra) == -1 || bolsaDeFichas.get(letra) == 0) {
					letra = (char)((char)new Random().nextInt(91 - 65) + 65);
				}
				repartirFichas(jugadores,bolsaDeFichas,idJugador,letra);
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
				puntajePalabra += PuntajeFichas.getPuntaje(letra + "") * tablero[i][d].getPuntos();					
			}
			else if(tablero[i][d].getClass() == PremioPalabra.class) {
				puntajePalabra += PuntajeFichas.getPuntaje(letra + "");
				cantVecesMultiplicar *= tablero[i][d].getPuntos();
				multiplicarPalabra = true;
			}
			else {
				puntajePalabra += PuntajeFichas.getPuntaje(letra + "");
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
	
	
	
	
	
	
	
	
	
	//Setters y Getters
	
	public Ficha[][] getTablero() {
		return tablero;
	}
	
	
}
