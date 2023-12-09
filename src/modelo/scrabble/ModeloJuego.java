package modelo.scrabble;

import java.rmi.RemoteException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import modelo.scrabble.Ficha;
import java.io.*;

public class ModeloJuego extends ObservableRemoto implements IModeloRemoto {
	
	// * TABLERO: 
	private Tablero tablero = new Tablero();
	// * BOLSA DE FICHAS: 
	private BolsaFichas bolsaDeFichas = new BolsaFichas();
	// * JUGADORES:
	private ArrayList<Jugador> usuariosJugadores = new ArrayList<>();
	private Jugador[] jugadores = new Jugador[4];
	// * TURNO:
	private int turnoActual = 0;
	// * OTROS:
	
	
	//INTERFAZ
	
	public void comenzarPartida(Jugador[] jugadores) throws RemoteException {
		
		//Creamos y agregamos a los jugadores
		addJugadores(jugadores);
		
		//Comenzamos la primer partida
		tablero.comenzarPartida(jugadores, bolsaDeFichas);
		
		notificarObservadores(Evento.NUEVA_PARTIDA);
		
	}
	
	
	public void devolverFichas(int idJugador, char[] fichasACambiar) throws RemoteException {
		
		//Devolvemos las fichas
		tablero.devolverFichas(jugadores, bolsaDeFichas, idJugador, fichasACambiar);
		
		notificarObservadores(Evento.CAMBIO_FICHAS);
		//notificarEstadoPartida();
	} 
	
	
	public void addPalabra(int idJugador, int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException {
		
		//Agregamos la palabra
		tablero.addPalabra(jugadores, bolsaDeFichas, idJugador, x, y, palabraActual, horizontal);
		try {
			notificarObservadores(Evento.NUEVA_PALABRA);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	
	public int siguienteTurno() throws RemoteException{
		if(this.jugadores[turnoActual] != null) {
			return turnoActual++;
		}
		else {
			turnoActual = 0;
		}
		return turnoActual;
	}
	
	public void cargarPartida(int idPartida) throws IOException, ClassNotFoundException{

		//Cargamos la lista de todas las partidas guardadas
		ArrayList<Object> listaPartidas = getListaPartidas();
		
		//Alias del objeto
		Partida partidaACargar = (Partida) listaPartidas.get(idPartida);
		
		//Volcamos todo el contenido en el modelo
		tablero = partidaACargar.getTablero();
		bolsaDeFichas = partidaACargar.getBolsaDeFichas();
		jugadores = partidaACargar.getJugadores();
		turnoActual = partidaACargar.getTurnoActual();
		
	}
	
	public void guardarPartida() throws IOException, RemoteException{

		//Creamos el archivo donde se guarda la partida
		FileOutputStream partida = new FileOutputStream("PartidasGuardadas.dat",true);
		ObjectOutputStream oos = new ObjectOutputStream(partida);
		
		//Creamos el objeto de la partida a guardar
		Partida partidaActual = new Partida(tablero,bolsaDeFichas,jugadores,turnoActual);
		
		//Guardamos el objeto en el archivo
		oos.writeObject(partidaActual);
		oos.close();
		
		notificarObservadores(Evento.PARTIDA_GUARDADA);
		
	}
	
	//a revisar
	public int getGanador() throws RemoteException{
		int ganador;
		if(jugadores[0].getPuntaje() > jugadores[1].getPuntaje()) {
			ganador = 0;
		}
		else {
			ganador = 1;
		}
		return ganador;
	}
	
	
	public boolean isPrimerMovimiento() throws RemoteException{
		return jugadores[0].getPuntaje() == 0 && jugadores[1].getPuntaje() == 0;
	}
	
	

	//Setters y Getters
	
	public Ficha[][] getTablero() throws RemoteException{
		return tablero.getTablero();
	}

	public int getTurnoActual() throws RemoteException{
		return turnoActual;
	}
	
	public BolsaFichas getBolsaDeFichas() throws RemoteException{
		return bolsaDeFichas;
	}
	
	public Jugador[] getJugadores() throws RemoteException{
		return jugadores;
	}
	
	public boolean isVacia() throws RemoteException{
		return bolsaDeFichas.getCantidadFichas() == 0;
	}
	
	public int getCantidadFichas() throws RemoteException{
		return bolsaDeFichas.getCantidadFichas();
	}
	
	public ArrayList<Object> getListaPartidas() throws IOException, ClassNotFoundException, RemoteException{

		//Creo una lista vacia de partidas
		ArrayList<Object> listaPartidas = new ArrayList<>();
		
		//Creo un FileInputStream con el nombre del archivo
		FileInputStream archivo = new FileInputStream("PartidasGuardadas.dat");
				
		//Creo un ObjectInputStream con el FileInputStream
		ObjectInputStream ois = new ObjectInputStream(archivo);
		
		//Leo el primer objeto del archivo
		try {
			Object objeto = ois.readObject();
			while(objeto != null) {
				listaPartidas.add(objeto);
				objeto = ois.readObject();
			}
			ois.close();
			
		} catch(EOFException e) {
			System.out.println("Fin de Archivo");
		}
		return listaPartidas;
	}
	
	
	//Metodos de observer
	
	private void addJugadores(Jugador[] jugadores) throws RemoteException {
		//Agrego los jugadores
		for(int j = 0; j < jugadores.length; j++) {
			this.jugadores[j] = jugadores[j];
			usuariosJugadores.add(jugadores[j]);
		}
		notificarObservadores(Evento.NUEVOS_JUGADORES);
	}
	
	private void removeJugadores(int idUsuario) throws RemoteException {
		usuariosJugadores.remove(idUsuario);
		notificarObservadores(Evento.NUEVOS_JUGADORES);
	}
	
	public void notificarObservadores(Object obj) throws RemoteException {
		super.notificarObservadores(obj);
	}
	
	

}
