package modelo.scrabble;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

public class ClasePrueba {
	
		private Ficha[][] tablero = new Ficha[16][16];

		private final int cantidadFichas = 7;
	
	
public void addPalabra(Jugador[] jugadores, BolsaFichas bolsaDeFichas, int idJugador, int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException {
		
		//PROCESAMIENTO:
		
		//Hago un alias del conjunto de letras de la palabra
		char[] letrasPalabra = palabraActual.getLetras();

		//Hago un alias del atril del jugador
		List<Character> atril = jugadores[idJugador].getAtril();
		
		//Hago un formateo de x e y
		x -= 64; y -=64;
		
		int primeraLetra = 0;
		int ultimaLetra = letrasPalabra.length - 1;
		
		//VALIDACIÓN:
		//[a,u,r,a]
		// 0 1 2 3
		
		if(tablero[x][y] != null &&
				!tablero[x][y].getLetra().equals(letrasPalabra[primeraLetra])) {
			System.out.println("La posición esta ocupada por una letra distinta.");;
		}
		else { //AGREGO LA PALABRA AL TABLERO
			//Coloco las letras en el tablero y se las resto del atril al jugador
			/*
			int i = x, d = y;
			if(horizontal) {
				for(Character l: letrasPalabra) {
				if(i == x && d == y) {
					
				}
					
				if(horizontal) {
					d++;				
				}
				else{
					i++;
				}
			}
	
			*/
			
		}
		
		
		
		
		
		
		
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

public void repartirFichas(Jugador[] jugadores, BolsaFichas bolsaDeFichas, int idJugador, char letra) {
	
	//Le agrego las fichas al atril del jugador
	jugadores[idJugador].getAtril().add(letra);

	//Le quito las fichas a la bolsa
	bolsaDeFichas.put(letra, bolsaDeFichas.get(letra) - 1);
	bolsaDeFichas.setCantidadFichas(bolsaDeFichas.getCantidadFichas() - 1);
}


	

}
