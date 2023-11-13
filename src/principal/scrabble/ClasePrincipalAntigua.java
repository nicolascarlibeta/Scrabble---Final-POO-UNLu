package principal.scrabble;

import modelo.scrabble.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.*;

import controlador.scrabble.*;
import vista.scrabble.*;

public class ClasePrincipalAntigua {
	
	//ArrayList<Map.Entry<Letra, Integer>> fichas = s.getFichas();
			/*
			for(Map.Entry<Letra, Integer> f: fichas) {
				System.out.println("" + f.getKey() + "Cantidad: " + f.getValue());
			}*/
	
	public static void main(String[] args) {
		
		ModeloJuego s = new ModeloJuego();
		s.comenzarPartida(1);
		System.out.println(s);
		
		System.out.println("Atril: " + s.getJugador());
		System.out.println("Puntaje: " + s.getJugador().getPuntaje());
		
		//System.out.print("Que palabra deseas agregar?: ");
		//int numero = new Scanner(System.in).nextInt();
		System.out.print("Coordenada horizontal? A, B, C, etc.: ");
		int x = new Scanner(System.in).nextInt();
		System.out.print("Coordenada vertical? 1, 2, 3, etc.: ");
		int y = new Scanner(System.in).nextInt();
		
		Character[] letras = new Character[4];
		letras[0] = 'Z';
		letras[1] = 'A';
		letras[2] = 'R';
		letras[3] = 'A';
		s.addPalabra(0, x, y, new Palabra(letras));
		
		System.out.println(s);
		System.out.println("Atril: " + s.getJugador());
		System.out.println("Puntaje: " + s.getJugador().getPuntaje());
		

		
	}
	
	


}
