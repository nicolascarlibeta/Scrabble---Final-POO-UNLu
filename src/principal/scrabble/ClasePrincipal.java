package principal.scrabble;

import vista.scrabble.*;
import modelo.scrabble.*;
import controlador.scrabble.*;

public class ClasePrincipal {

	public static void main(String[] args) {
		
		ModeloJuego modelo = new ModeloJuego();
		Controlador controlador = new Controlador(modelo);
		VistaConsola vista = new VistaConsola(controlador);
		
		vista.mostrarComenzarPartida();
		
		
	}

}
