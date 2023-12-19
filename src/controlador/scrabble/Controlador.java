package controlador.scrabble;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.scrabble.*;
import vista.scrabble.Vista;

public class Controlador implements IControladorRemoto{
	
	private Vista vista;
	private IModeloRemoto modelo;
	
	//CONSTRUCTOR
	public Controlador(IModeloRemoto modelo) {
		try {
			setModeloRemoto(modelo);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public Controlador() {}
	
	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
	public <T extends IObservableRemoto> void setModeloRemoto(T modelo) throws RemoteException {
		this.modelo = (IModeloRemoto) modelo;
	}
	
	
	public void comenzarPartida(Jugador[] jugadores) {
		try {
			modelo.comenzarPartida(jugadores);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	
	public void cambiarFichas(int idJugador, char[] cadenaCaracteres) {
		try {
			modelo.devolverFichas(idJugador,cadenaCaracteres);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	
	public void agregarPalabra(int idJugador, int x, int y, String cadenaString, boolean horizontal) {
		
		//Creo la nueva palabra dentro del Controlador
		Palabra nuevaPalabra = new Palabra(cadenaString);
		
		//La envio al modelo
		try {
			modelo.agregarPalabra(idJugador, x, y, nuevaPalabra, horizontal);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
	
	public boolean validarPalabra(int x, int y, String cadenaString, boolean horizontal) {
		
		//Creo la nueva palabra dentro del Controlador
		Palabra nuevaPalabra = new Palabra(cadenaString);
				
		//La envio al modelo
		try {
			return modelo.validarPalabra(x, y, nuevaPalabra, horizontal);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void cargarPartida(int idPartida) throws IOException{
		try {
			modelo.cargarPartida(idPartida);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public void guardarPartida() throws IOException{
		try {
			modelo.guardarPartida();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public ArrayList<Jugador> obtenerTop5Jugadores() throws IOException{
		try {
			return modelo.getTop5Jugadores();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Partida> obtenerPartidas() throws IOException{
		try {
			return modelo.getListaPartidas();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return null;
		}
	}
	
	public Ficha[][] obtenerTablero() {
		try {
			return modelo.getTablero();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			return null;
		}
	}
	
	public int obtenerGanador() throws RemoteException{
		return modelo.obtenerGanador();
	}
	
	public Jugador[] obtenerJugadores() throws RemoteException{
		return modelo.getJugadores();
	}
	
	public Jugador obtenerJugadores(int idJugador){
		try {
			return modelo.getJugadores()[idJugador];
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			return null;
		}
	}
	
	public boolean bolsaEstaVacia() throws RemoteException {
		return modelo.isVacia();
	}
	
	public int obtenerCantidadFichas() throws RemoteException {
		return modelo.getCantidadFichas();
	}
	
	public boolean esPrimerMovimiento() {
		try {
			return modelo.isPrimerMovimiento();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return false;
		}
	}
	
	public int siguienteTurno() {
		try {
			return modelo.siguienteTurno();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			return -1;
		}
	}
	
	public int obtenerTurnoActual() {
		try {
			return modelo.getTurnoActual();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			return -1;
		}
	}
	
	public void pasarTurno() {
		try {
			modelo.pasarTurno();
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
		if(arg1 instanceof Evento) {
			switch ((Evento) arg1) {
			case NUEVOS_JUGADORES -> {
				Jugador[] nuevosJugadores = modelo.getJugadores();
				vista.mostrarIngresarJugadores();				
				}
			case NUEVA_PARTIDA -> {
				Jugador[] jugadores = modelo.getJugadores();
				vista.mostrarComenzarPartida(jugadores);				
				}
			case PARTIDA_CARGADA -> {
				vista.mostrarMensaje("Se ha cargado la partida exitosamente.");				
				}
			case PARTIDA_GUARDADA -> {
				vista.mostrarMensaje("Se ha guardado la partida.");
				try {
					vista.mostrarPartidasGuardadas(modelo.getListaPartidas());
				} catch (ClassNotFoundException | IOException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				}
			case NUEVA_PALABRA -> {
				vista.mostrarMensaje("Se ha agregado la palabra correctamente.");				
				}
			case CAMBIO_FICHAS -> {
				vista.mostrarMensaje("Se han cambiado las fichas correctamente.");				
				}
			case CAMBIO_ESTADO_PARTIDA -> {
				Ficha[][] tablero = modelo.getTablero();
				int turnoActual = modelo.getTurnoActual();
				Jugador jugadorActual = modelo.getJugadores()[turnoActual];
				vista.mostrarTablero(tablero);	
				vista.mostrarEstadoJugador(jugadorActual);				
				}
			}
		}
	}
	
	

	

	

	

	

	
	

}
