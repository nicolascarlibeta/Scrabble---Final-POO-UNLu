package ar.edu.unlu.poo.scrabble;


public class ClasePrincipal {

	public static void main(String[] args) {
		Scrabble scrabble = new Scrabble();
		System.out.println(scrabble);
		System.out.println("Puntaje: " + scrabble.getPuntajePalabra());
		System.out.println(" ");
		scrabble.addLetra(2, 1, new Letra("Z",10));
		scrabble.addLetra(2, 2, new Letra("A",3));
		System.out.println(scrabble);
		System.out.println("Puntaje: " + scrabble.getPuntajePalabra());

	}

}
