package controlador.scrabble;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
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
		
		
		public IJugador agregarJugador(String nombreJugador) {
			
			IJugador jugador = new Jugador(nombreJugador);
			try {
				modelo.addJugador((Jugador) jugador);
				return jugador;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		
		public void desconectarJugador(IJugador cliente) {
			try {
				modelo.desconectarJugador((Jugador) cliente);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void comenzarPartida() {
			try {
				modelo.comenzarPartida();
			} catch (RemoteException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		
		
		public boolean agregarPalabra(String x, String y, String cadenaString, String disposicion) {
			
			//Creo la nueva palabra dentro del Controlador
			Palabra nuevaPalabra = new Palabra(cadenaString);
			
			//Casteo las coordenadas antes de agregarlas al Modelo
			x = x.toUpperCase();
			y = y.toUpperCase();
			
			try {
				return modelo.agregarPalabra(x, y, nuevaPalabra, disposicion);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}
		
		
		public boolean cambiarFichas(String cadena) {
			cadena = cadena.toUpperCase();
			char[] cadenaCaracteres = cadena.toCharArray();
			try {
				return modelo.cambiarFichas(cadenaCaracteres);
			} catch (RemoteException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
				return false;
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
		
		
		public String validarDisposicion(boolean horizontal) {
			String disp = "";
			if(horizontal) {
				disp = "1";
			}
			else {
				disp = "2";
			}
			return disp;
		}
		
		
		public boolean validarFlujo() {
			boolean avanzar = true;
			try {
				if(modelo.isPrimerMovimiento()) {
					avanzar = false;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return avanzar;
		}
		
		
		public void cargarPartida(int idPartida) throws IOException{
			try {
				modelo.cargarPartida(idPartida);			
			}
			catch (ClassNotFoundException | IOException e) {
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
		
		
		public ArrayList<IJugador> obtenerTop5Jugadores() throws IOException{
			ArrayList<Jugador> top5Jugadores = new ArrayList<>();
			try {
				top5Jugadores = modelo.getTop5Jugadores();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			ArrayList<IJugador> listaTop5 = new ArrayList<>();		
			for(IJugador j: top5Jugadores) {
				listaTop5.add(j);
			}
			return listaTop5;
		}
		
		
		public ArrayList<IPartida> obtenerPartidas() throws IOException{
			
			ArrayList<Partida> partidas = new ArrayList<>();
			try {
				partidas = modelo.getListaPartidas();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			ArrayList<IPartida> listaPartidas = new ArrayList<>();		
			for(Partida p: partidas) {
				listaPartidas.add(p);
			}
			return listaPartidas;
		}
		
		
		public Casillero[][] obtenerTablero() {
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
		
		
		public ArrayList<IJugador> obtenerJugadores() throws RemoteException{
			ArrayList<Jugador> jugadores = new ArrayList<>();
			try {
				jugadores = modelo.getJugadores();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ArrayList<IJugador> listaJugadores = new ArrayList<>();		
			for(IJugador j: jugadores) {
				listaJugadores.add(j);
			}
			return listaJugadores;
		}
		
		
		public IJugador obtenerJugadores(int idJugador){
			try {
				return modelo.getJugadores().get(idJugador);
			} catch (RemoteException e) {
				return null;
			}
		}
		
		
		public boolean bolsaEstaVacia() throws RemoteException {
			return modelo.isVacia();
		}
		
		
		public int obtenerCantidadFichas() {
			try {
				return modelo.getCantidadFichas();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				case NUEVO_JUGADOR -> {
					vista.mostrarMensaje("El usuario se ha conectado exitosamente.");				
					}
				case JUGADOR_DESCONECTADO -> {
					vista.mostrarMensaje("El usuario se ha desconectado.");				
					}
				case NUEVA_PARTIDA -> {
					vista.mostrarComenzarPartida(obtenerJugadores());				
					}
				case PARTIDA_CARGADA -> {
					vista.mostrarMensaje("Se ha cargado la partida exitosamente.");				
					}
				case PARTIDA_GUARDADA -> {
					vista.mostrarMensaje("Se ha guardado la partida.");
					try {
						vista.mostrarPartidasGuardadas(obtenerPartidas());
					} catch (IOException e) {
						e.printStackTrace();}
					}
				case NUEVA_PALABRA -> {
					vista.mostrarMensaje("Se ha agregado la palabra correctamente.");				
					}
				case CAMBIO_FICHAS -> {
					vista.mostrarMensaje("Se han cambiado las fichas correctamente.");				
					}
				case CAMBIO_ESTADO_PARTIDA -> {
					Casillero[][] tablero = modelo.getTablero();
					int turnoActual = modelo.getTurnoActual();
					IJugador jugadorActual = modelo.getJugadores().get(turnoActual);
					vista.mostrarTablero(tablero);	
					vista.mostrarEstadoJugador(jugadorActual);				
					}
				case ERROR_ATRIL -> {
					vista.mostrarMensaje("<Ingrese una palabra que contenga las letras de su atril.>");				
					}
				case ERROR_CARGA_PARTIDAS -> {
					vista.mostrarMensaje("<Ingrese un número que corresponda al ID de la partida.>");
					}
				case ERROR_COORDENADAS -> {
					vista.mostrarMensaje("<Ingrese una letra coordenada entre A y O.>");
					}
				case ERROR_DICCIONARIO -> {
					vista.mostrarMensaje("<La palabra ingresada no es valida, intente con otra.>");
					}
				case ERROR_VALIDACION_PALABRA -> {
					vista.mostrarMensaje("<La palabra debe al menos estar en contacto con una ficha ya existente.>");
					}
				case ERROR_DISPOSICION -> {
					vista.mostrarMensaje("<Ingrese un número valido entre 1 y 2.>");
					}
				}
			}
		}

		

	
	

}
