package vista.scrabble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import controlador.scrabble.Controlador;
import modelo.scrabble.IJugador;
import modelo.scrabble.IPartida;
import modelo.scrabble.Casillero;
import modelo.scrabble.Jugador;

public class VistaGrafica implements Vista{

	//Como siempre inicio un tipo de Vista en el Cliente (Vista Gráfica, Consola, Mobile, etc.),
	//debo tener referencia del <JUGADOR> asociado a dicho cliente.
	private String nombreJugador = "";
	private IJugador cliente;
	private Controlador controlador;
	private VentanaPrincipal ventanaPrincipal;
	private VentanaPartidas ventanaPartidas;
	private VentanaTablero ventanaTablero;
	private VentanaRanking ventanaRanking;

	
	//CONSTRUCTOR
	public VistaGrafica(Controlador controlador, String nombreJugador) {
		
		this.controlador = controlador;
		this.nombreJugador = nombreJugador.toUpperCase();
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
				controlador.comenzarPartida();				
			}
		});
		
		
		//Acción de Ingresar palabra
		ventanaTablero.ingresarPalabra(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(esTurnoActual()) {
					String cadenaString = ventanaTablero.recibirPalabra().toUpperCase();
					String x = ventanaTablero.recibirCoordenadaX();
					String y = ventanaTablero.recibirCoordenadaY();
					String disposicion = null;
					disposicion = controlador.validarDisposicion(ventanaTablero.isSelected());
					
					if(controlador.agregarPalabra(x,y,cadenaString,disposicion)) {
						ventanaTablero.limpiar();					
					}
				}
				else {
					mostrarMensaje("<Espere a que los demas terminen su turno>");
					}
				}
			});
		
		
		//Acción de cambiar fichas
		ventanaTablero.cambiarFichas(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(esTurnoActual()) {
					String cadenaString = ventanaTablero.recibirCadenaString();
					if(controlador.cambiarFichas(cadenaString)) {
						ventanaTablero.limpiar();					
					}
				}
				else {
					mostrarMensaje("<Espere a que los demas terminen su turno>");
				}
				
			}
		});
		
		
		//Acción de pasar turno
		ventanaTablero.pasarTurno(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(esTurnoActual()) {
					controlador.pasarTurno();					
				}
				else {
					mostrarMensaje("<Espere a que los demas terminen su turno>");
				}
			}
		});
		
		
		//Acción de desconectar jugador
		ventanaTablero.desconectar(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controlador.desconectarJugador(cliente);
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
					
					e1.printStackTrace();
				}	
			}
		});
	}	

	//Método para controlar los eventos del jugador del turno actual
	public boolean esTurnoActual() {
		IJugador jugadorTurnoActual = null;
		jugadorTurnoActual = controlador.obtenerJugadores(controlador.obtenerTurnoActual());
		return cliente.equals(jugadorTurnoActual);
	}
	
	//Métodos de VistaGrafica
	public void iniciar() {
		ventanaPrincipal.setVisible(true);
		this.cliente = controlador.agregarJugador(nombreJugador);
	}

	public void mostrarComenzarPartida(ArrayList<IJugador> jugadores) {
		mostrarComenzarPartida();
		ventanaTablero.mostrarMensaje("Comienza la partida. Empieza el jugador " + jugadores.get(controlador.obtenerTurnoActual()).getNombre() + ".");
	}
	
	public void mostrarComenzarPartida() {
		ventanaTablero.setCliente(cliente);
		ventanaTablero.setVisible(true);
	}

	
	public void mostrarTablero(Casillero[][] tablero) {
		ventanaTablero.mostrarTablero(tablero);
	}

	
	public void mostrarEstadoJugador(IJugador jugador) {
		int cantidadFichas = 0;
		cantidadFichas = controlador.obtenerCantidadFichas();
		ventanaTablero.mostrarEstadoJugador(jugador, cantidadFichas);
	}
	

	public void mostrarPartidasGuardadas(ArrayList<IPartida> partidas) {
		ventanaPartidas.mostrarPartidasGuardadas(partidas);
	}
	
	public void mostrarPartidasGuardadas() {
		ventanaPartidas.setVisible(true);
	}

	public void mostrarRanking() {
		try {
			ventanaRanking.setVisible(true);
			ventanaRanking.mostrarRanking(controlador.obtenerTop5Jugadores());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarMensaje(String mensaje) {
		ventanaTablero.mostrarMensaje(mensaje);
	}
	
	
	


	
	
	
}
	

