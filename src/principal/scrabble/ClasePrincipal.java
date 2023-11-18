package principal.scrabble;

import vista.scrabble.*;
import vista.scrabble.consolagrafica.ConsolaGrafica;
import modelo.scrabble.*;

import java.awt.EventQueue;

import controlador.scrabble.*;

public class ClasePrincipal {

	//Método Main (EJECUTABLE)
	public static void main(String[] args) {
			
		ModeloJuego modelo = new ModeloJuego();
		Controlador controlador = new Controlador(modelo);
		//La linea de abajo no es del todo cohesiva
		//modelo.addJugador("Javier Carli");
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsolaGrafica vista = new ConsolaGrafica(controlador);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
