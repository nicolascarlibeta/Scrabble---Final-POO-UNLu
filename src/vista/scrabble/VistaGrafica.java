package vista.scrabble;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import controlador.scrabble.Controlador;
import modelo.scrabble.Diccionario;
import modelo.scrabble.Ficha;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.Flujo;
import vista.scrabble.consolagrafica.FlujoFinalPartida;
import vista.scrabble.consolagrafica.FlujoIngresarPalabra;
import vista.scrabble.consolagrafica.FlujoOpcionesJuego;
import vista.scrabble.consolagrafica.FlujoIngresarPalabra.EstadosPosibles;

public class VistaGrafica implements Vista{

	private Controlador controlador;
	private VentanaPrincipal ventanaPrincipal;
	private VentanaPartidas ventanaPartidas;
	private VentanaTablero ventanaTablero;

	
	//CONSTRUCTOR
	public VistaGrafica(Controlador controlador) {
		
		this.controlador = controlador;
		ventanaPrincipal = new VentanaPrincipal();
		ventanaPartidas = new VentanaPartidas();
		ventanaTablero = new VentanaTablero();
		controlador.setVista(this);
		
		//FLUJO
		
		// * Nueva Partida
		//Acción de Nueva Partida
		ventanaPrincipal.nuevaPartida(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//Ingreso cuantas personas van a jugar en la partida actual
				String cantidadJugadores = JOptionPane.showInputDialog("¿Cuántas personas van a jugar (entre 2 y 4)?");
				int cantJug = ventanaPrincipal.validarCantidadJugadores(cantidadJugadores);
				while(cantJug == -1) {
					JOptionPane.showMessageDialog(ventanaPrincipal.parentComponent(),"***¡Debe ingresar un número valido entre 2 y 4!***");
					cantidadJugadores = JOptionPane.showInputDialog("¿Cuántas personas van a jugar (entre 2 y 4)?");
					cantJug = ventanaPrincipal.validarCantidadJugadores(cantidadJugadores);
				}
				
				//Ingreso los nombres de los jugadores
				Jugador[] jugadores = new Jugador[cantJug];
				for(int j = 0; j < cantJug; j++) {
					String nomJugador = JOptionPane.showInputDialog("¿Cómo se llamará el jugador " +  (j + 1)  + "?:");
					jugadores[j] = new Jugador(nomJugador.toUpperCase());
				}
				controlador.comenzarPartida(jugadores);				
			}
		});
		
		
		//Acción de ingresar palabra
		ventanaTablero.ingresarPalabra(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int idJugador = controlador.siguienteTurno();
				Jugador jugadorActual = controlador.obtenerJugadores(idJugador);
				mostrarEstadoJugador(jugadorActual);
				boolean avanzar = true;
				
				try {
					if(controlador.obtenerJugadores(idJugador).getAtril().isEmpty()
							&& controlador.bolsaEstaVacia()) {
						int idGanador = controlador.obtenerGanador();
						JOptionPane.showMessageDialog(ventanaTablero.parentComponent(), "El juego ha terminado. ¡Felicidades " + controlador.obtenerJugadores(idGanador).getNombre() + ", sos el ganador!");
						avanzar = false;
					}
				} catch (HeadlessException | RemoteException e1) {
					
					e1.printStackTrace();
				}
				
				if(avanzar){
					//Primero recibo la palabra
					String cadenaString = validarPalabra(idJugador);
					int x = 0, y = 0;
					if(!controlador.esPrimerMovimiento()) {
						//Coor. X
						x = validarCoorX();
						//Coor. Y
						y = validarCoorY();					
					}
					//Disposición
					validarDisposicion();
					
					//Ingreso la palabra
					controlador.agregarPalabra(idJugador,x,y,cadenaString,false);					
				}
			
			}
		});
		
		
		//Acción de cambiar fichas
		ventanaTablero.cambiarFichas(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				Jugador jugadorActual = null;
				String cadenaString = ventanaTablero.recibirCadenaString();
				jugadorActual = controlador.obtenerJugadores(controlador.obtenerTurnoActual());
		    	cadenaString = cadenaString.toUpperCase();
				char[] cadenaCaracteres = cadenaString.toCharArray();
				for(char c: cadenaCaracteres) {
					if(jugadorActual != null && !jugadorActual.getAtril().contains(c)) {
						mostrarMensaje("Ingrese una palabra que contenga las letras de su atril.");
					}
				}
				controlador.cambiarFichas(controlador.obtenerTurnoActual(), cadenaCaracteres);
			}
		});
		
		
		//Acción de pasar turno
		ventanaTablero.pasarTurno(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controlador.siguienteTurno();
			}
		});
		
	}	

	
	public void iniciar() {
		ventanaPrincipal.setVisible(true);
	}
	
	
	public void mostrarIngresarJugadores() {
		ventanaTablero.mostrarMensaje("Se han ingresado los nuevos jugadores");
	}

	
	public void mostrarComenzarPartida(Jugador[] jugadores) {
		ventanaPrincipal.setVisible(false);
		ventanaTablero.setVisible(true);
		mostrarTablero(controlador.obtenerTablero());
		ventanaTablero.mostrarMensaje("Comienza la partida. Empieza el jugador " + jugadores[0].getNombre() + ".");
		
	}

	
	public void mostrarTablero(Ficha[][] tablero) {
		String obtenerTablero = "";
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				obtenerTablero += tablero[f][c].getLetra() + " ";
			}
			obtenerTablero += "\n"; 
		}
		ventanaTablero.mostrarTablero(obtenerTablero);
	}

	
	public void mostrarEstadoJugador(Jugador jugador) {
		ventanaTablero.mostrarEstadoJugador(jugador);
	}

	
	public void mostrarMensaje(String mensaje) {
		ventanaTablero.mostrarMensaje(mensaje);
	}
	
	//Métodos del flujo
	public String validarPalabra(int idJugador) {
		
		//Primero recibo la palabra
		String cadenaString = ventanaTablero.recibirPalabra();
		Jugador jugadorActual = controlador.obtenerJugadores(idJugador);
		cadenaString = cadenaString.toUpperCase();
		char[] cadenaCaracteres = cadenaString.toCharArray();
		
		//Luego, valido que la palabra contenga letras del atril
		for(char c: cadenaCaracteres) {
			if(!jugadorActual.getAtril().contains(c)) {
				mostrarMensaje("Ingrese una palabra que contenga las letras de su atril.");
			}
		}
		
		//Valido la palabra en el diccionario
		try {
			if(!new Diccionario().contieneA(cadenaString.toLowerCase())) {
				mostrarMensaje("La palabra ingresada no es valida, intente con otra.");
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		return cadenaString;
	}
	
	
	public int validarCoorX() {
		String xString = ventanaTablero.recibirCoordenadaX();
		xString = xString.toUpperCase();
		int x = (int) xString.toCharArray()[0];
		if(x < 65 || x > 79) {
			mostrarMensaje("Ingrese una letra coordenada entre A y O.");
		}
		return x;
	}
	
	
	public int validarCoorY() {
		String yString = ventanaTablero.recibirCoordenadaY();
		yString = yString.toUpperCase();
		int y = (int) yString.toCharArray()[0];
		if(y < 65 || y > 79) {
			mostrarMensaje("Ingrese una letra coordenada entre A y O.");
		}
		return y;
	}
	
	public void validarDisposicion() {
		/*
		switch(opcion) {
		case "1" -> horizontal = true;
		case "2" -> horizontal = false;
		default -> {
			vista.mostrarMensaje("Ingrese un número valido entre 1 y 2.");
			return this;}
		}
		*/
	}

	
	
}
	

