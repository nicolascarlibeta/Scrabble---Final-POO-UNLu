package principal.scrabble;

import vista.scrabble.*;
import vista.scrabble.consolagrafica.ConsolaGrafica;
import modelo.scrabble.*;
import java.awt.EventQueue;
import servidor.scrabble.*;
import cliente.scrabble.*;
import controlador.scrabble.*;

public class ClasePrincipal {

	//Método Main (EJECUTABLE)
	public static void main(String[] args) {
		
		AppServidor servidor = new AppServidor();
		servidor.main(args);
		
		AppCliente cliente = new AppCliente();
		AppClienteVistaGrafica clienteVG = new AppClienteVistaGrafica();
		cliente.main(args);
		clienteVG.main(args);
		
	}
	
}
