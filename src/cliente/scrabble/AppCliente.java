package cliente.scrabble;

import java.rmi.RemoteException;
import java.util.ArrayList;
import vista.scrabble.*;
import vista.scrabble.consolagrafica.ConsolaGrafica;
import javax.swing.JOptionPane;
import controlador.scrabble.*;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;

public class AppCliente {

	public static void main(String[] args) {
		ArrayList<String> ips = Util.getIpDisponibles();
		String ip = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la que escuchar� peticiones el cliente", "IP del cliente", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				ips.toArray(),
				null
		);
		String port = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que escuchar� peticiones el cliente", "Puerto del cliente", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				9999
		);
		String ipServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la corre el servidor", "IP del servidor", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				null,
				null
		);
		String portServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que corre el servidor", "Puerto del servidor", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				8888
		);
		String nombreJugador = (String) JOptionPane.showInputDialog(
				null, 
				"¿Cómo se llamará el jugador?", "Nombre del jugador", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				"Juan, jose, anonimo48_, ..."
				);
		Controlador controlador = new Controlador();
		Vista vista = new ConsolaGrafica(controlador, nombreJugador);
		Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));
		try {
			/*
			 * el método iniciar() carga al nuevo Cliente y determina que el modelo (servidor)
			pertenece al nuevo Controlador
			
			*/
			c.iniciar(controlador);
			vista.iniciar();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RMIMVCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
