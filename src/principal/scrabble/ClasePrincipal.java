package principal.scrabble;

import vista.scrabble.*;
import vista.scrabble.consolagrafica.ConsolaGrafica;
import modelo.scrabble.*;
import java.awt.EventQueue;

import controlador.scrabble.*;

public class ClasePrincipal {

	//Método Main (EJECUTABLE)
	public static void main(String[] args) {
			
		//IModeloRemoto modelo = new ModeloJuego();
		IModeloRemoto modelo = new ModeloJuego();
		Controlador controlador1 = new Controlador(modelo);
		Controlador controlador2 = new Controlador(modelo);
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsolaGrafica vista1 = new ConsolaGrafica(controlador1);
					VistaGrafica vista2 = new VistaGrafica(controlador2);
					vista1.iniciar();
					//vista2.iniciar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
