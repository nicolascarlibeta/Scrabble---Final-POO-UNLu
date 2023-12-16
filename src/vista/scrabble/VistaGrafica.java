package vista.scrabble;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private VentanaRanking ventanaRanking;

	
	//CONSTRUCTOR
	public VistaGrafica(Controlador controlador) {
		
		this.controlador = controlador;
		ventanaPrincipal = new VentanaPrincipal();
		ventanaPartidas = new VentanaPartidas();
		ventanaTablero = new VentanaTablero();
		ventanaRanking = new VentanaRanking();
		controlador.setVista(this);
		
		//FLUJO
		
		// * Nueva Partida
		//Acción de Nueva Partida
		ventanaPrincipal.nuevaPartida(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//Ingreso cuantas personas van a jugar en la partida actual
				String cantidadJugadores = JOptionPane.showInputDialog("¿Cuántas personas van a jugar (entre 2 y 4)?");
				int cantJug = Integer.parseInt(cantidadJugadores);					
				while(cantJug < 2 || cantJug > 4) {
					JOptionPane.showMessageDialog(ventanaPrincipal.parentComponent(),"***¡Debe ingresar un número valido entre 2 y 4!***");
					cantidadJugadores = JOptionPane.showInputDialog("¿Cuántas personas van a jugar (entre 2 y 4)?");
					cantJug = Integer.parseInt(cantidadJugadores);
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
		
		
		//Acción de Ingresar palabra
		ventanaTablero.ingresarPalabra(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int idJugador = controlador.obtenerTurnoActual();
				Jugador jugadorActual = controlador.obtenerJugadores(idJugador);
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
				
				//Primero recibo la palabra
				String cadenaString = validarPalabra(idJugador);
				if(cadenaString.equals("")) {
					avanzar = false;
				}
				
				int x = 72, y = 72;
				if(!controlador.esPrimerMovimiento()) {
					//Coor. X
					x = validarCoorX();
					//Coor. Y
					y = validarCoorY();
					if(x == -1 || y == -1) {
						avanzar = false;
					}
				}
					
				//Disposición
				boolean disposicion = validarDisposicion();
					
				//Ingreso la palabra
				if(avanzar) {
					ventanaTablero.limpiar();
					controlador.agregarPalabra(idJugador,x,y,cadenaString,disposicion);						
				}
			}
		});
		
		
		//Acción de cambiar fichas
		ventanaTablero.cambiarFichas(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				boolean avanzar = true;
				Jugador jugadorActual = null;
				String cadenaString = ventanaTablero.recibirCadenaString();
				jugadorActual = controlador.obtenerJugadores(controlador.obtenerTurnoActual());
		    	cadenaString = cadenaString.toUpperCase();
				char[] cadenaCaracteres = cadenaString.toCharArray();
				for(char c: cadenaCaracteres) {
					if(jugadorActual != null && !jugadorActual.getAtril().contains(c)) {
						mostrarMensaje("Ingrese una palabra que contenga las letras de su atril.");
						avanzar = false;
					}
				}
				if(avanzar) {
					controlador.cambiarFichas(controlador.obtenerTurnoActual(), cadenaCaracteres);					
				}
			}
		});
		
		
		//Acción de pasar turno
		ventanaTablero.pasarTurno(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controlador.pasarTurno();
			}
		});
		
		
		// * Cargar Partida
		//Acción de Cargar Partida
		ventanaPrincipal.cargarPartida(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				mostrarPartidasGuardadas();		
			}
		});
		
		//Acción de Elegir partida
		ventanaPartidas.elegirPartida(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {

				int idPartida = ventanaPartidas.getSelectedIndex();
				if(idPartida != -1) {
					try {
						controlador.cargarPartida(idPartida);
						mostrarComenzarPartida();
						ventanaPartidas.setVisible(false);
					} catch (IOException e1) {
						// TODO Bloque catch generado automáticamente
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		// * Ver Ranking
		//Acción de Ver Ranking
		ventanaPrincipal.verRanking(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				mostrarRanking();		
			}
		});
		
		//Cerrar Ventana
		ventanaTablero.cerrar(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				try {
					controlador.guardarPartida();
				} catch (IOException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}	
			}
		});
	
	

	}	

	
	//Métodos de VistaGrafica
	public void iniciar() {
		ventanaPrincipal.setVisible(true);
	}
	
	
	public void mostrarIngresarJugadores() {
		ventanaTablero.mostrarMensaje("Se han ingresado los nuevos jugadores");
	}

	
	public void mostrarComenzarPartida(Jugador[] jugadores) {
		ventanaTablero.setVisible(true);
		ventanaTablero.mostrarMensaje("Comienza la partida. Empieza el jugador " + jugadores[controlador.obtenerTurnoActual()].getNombre() + ".");
	}
	
	public void mostrarComenzarPartida() {
		ventanaTablero.setVisible(true);
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
	

	public void mostrarPartidasGuardadas() {
		try {
			ventanaPartidas.setVisible(true);
			ventanaPartidas.mostrarPartidasGuardadas(controlador.obtenerPartidas());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}


	public void mostrarRanking() {
		try {
			ventanaRanking.setVisible(true);
			ventanaRanking.mostrarRanking(controlador.obtenerTop5Jugadores());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	
	public void mostrarMensaje(String mensaje) {
		ventanaTablero.mostrarMensaje(mensaje);
	}
	
	
	//Métodos del flujo
	public String validarPalabra(int idJugador) {
		
		//Primero recibo la palabra
		String cadenaString = ventanaTablero.recibirPalabra();
		if(cadenaString.isEmpty()) {
			return "";
		}
		Jugador jugadorActual = controlador.obtenerJugadores(idJugador);
		cadenaString = cadenaString.toUpperCase();
		char[] cadenaCaracteres = cadenaString.toCharArray();
		
		//Luego, valido que la palabra contenga letras del atril
		if(!validarCaracteres(cadenaCaracteres,jugadorActual)) {
			mostrarMensaje("Ingrese una palabra que contenga las letras de su atril.");
			return "";
		}
		
		//Valido la palabra en el diccionario
		try {
			if(!new Diccionario().contieneA(cadenaString.toLowerCase())) {
				mostrarMensaje("La palabra ingresada no es valida, intente con otra.");
				cadenaString = "";
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		return cadenaString;
	}
	
	private boolean validarCaracteres(char[] cadenaCaracteres, Jugador jugadorActual) {
		for(char c: cadenaCaracteres) {
			if(!jugadorActual.getAtril().contains(c)) {
				return false;
			}
		}
		return true;
	}
	
	public int validarCoorX() {
		String xString = ventanaTablero.recibirCoordenadaX();
		xString = xString.toUpperCase();
		int x = (int) xString.toCharArray()[0];
		if(x < 65 || x > 79) {
			x = -1;
			mostrarMensaje("Ingrese una letra coordenada entre A y O.");
		}
		return x;
	}
	
	
	public int validarCoorY() {
		String yString = ventanaTablero.recibirCoordenadaY();
		yString = yString.toUpperCase();
		int y = (int) yString.toCharArray()[0];
		if(y < 65 || y > 79) {
			y = -1;
			mostrarMensaje("Ingrese una letra coordenada entre A y O.");
		}
		return y;
	}
	
	
	public boolean validarDisposicion() {
		
		boolean disposicion = false;
		if(ventanaTablero.isSelected()) {
			disposicion = true;
		}
		return disposicion;
		
	}
	
	


	
	
	
}
	

