package modelo.scrabble;

import java.rmi.RemoteException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import modelo.scrabble.Ficha;
import java.io.*;
import utilidades.scrabble.*;

public class ModeloJuego extends ObservableRemoto implements IModeloRemoto {
	
	// * TABLERO: 
	private Tablero tablero = new Tablero();
	// * BOLSA DE FICHAS: 
	private BolsaFichas bolsaDeFichas = new BolsaFichas();
	// * JUGADORES:
	private ArrayList<Jugador> usuariosJugadores = new ArrayList<>();
	private Jugador[] jugadores = new Jugador[4];
	// * TURNO:
	private int turnoActual = -1;
	// * OTROS:
	private String partidas = "PartidasGuardadas.dat";
	
	
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

	} 
	
	
	public void agregarPalabra(int idJugador, int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException {
		
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
		
		int turnoActual = 0; 
		if(this.jugadores[++this.turnoActual] != null) {
			turnoActual = this.turnoActual;
		}
		else {
			this.turnoActual = 0;
		}
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
		return turnoActual;
		
	}
	
	
	public int obtenerGanador() throws RemoteException{
		int mayor = jugadores[0].getPuntaje();
		int idGanador = 0;
		for(int j = 1; j < jugadores.length; j++) {
			if(jugadores[j].getPuntaje() > mayor) {
				mayor = jugadores[j].getPuntaje();
				idGanador = j;
			}
		}
		return idGanador;
	}
	
	
	public boolean isPrimerMovimiento() throws RemoteException{
		boolean esPrimer = true;
		for(Jugador j: jugadores) {
			if (j != null && j.getPuntaje() > 0) {
				return false;
			}
		}
		return esPrimer;
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
	
	
	//Lectura de archivos
	
	public void cargarPartida(int idPartida) throws IOException, ClassNotFoundException{

		//Cargamos la lista de todas las partidas guardadas
		ArrayList<Object> listaPartidas = getListaPartidas();
		
		//Alias del objeto
		Partida partidaACargar = (Partida) listaPartidas.get(idPartida - 1);
		
		//Volcamos todo el contenido en el modelo
		tablero = partidaACargar.getTablero();
		bolsaDeFichas = partidaACargar.getBolsaDeFichas();
		jugadores = partidaACargar.getJugadores();
		turnoActual = partidaACargar.getTurnoActual();
		
		notificarObservadores(Evento.PARTIDA_CARGADA);
		
	}
	
	
	public void guardarPartida() throws IOException{
		
		//Partida actual
		Partida partidaActual = new Partida(tablero,bolsaDeFichas,jugadores,turnoActual);
		
		Serializador serializador = new Serializador(partidas);
		
		Object o = serializador.readFirstObject();
		if(o == null) {
			serializador.writeOneObject(partidaActual);
		}
		else {
			serializador.addOneObject(partidaActual);
		}

		notificarObservadores(Evento.PARTIDA_GUARDADA);
		
	}
	
	
	public ArrayList<Object> getListaPartidas() throws IOException, ClassNotFoundException, RemoteException{

		//Creo una lista vacia de partidas
		ArrayList<Object> listaPartidas = new ArrayList<>();
		
		Serializador serializador = new Serializador(partidas);
		
		Object objeto = serializador.readFirstObject();
		if(objeto != null) {
			listaPartidas = serializador.readObjects();
		}
		
		/*
		//Creo un FileInputStream con el nombre del archivo
		FileInputStream archivo = new FileInputStream(partidas);
				
		//Creo un ObjectInputStream con el FileInputStream
		ObjectInputStream ois = new ObjectInputStream(archivo);
		
		//Leo el primer objeto del archivo
		try {
			Object objeto = ois.readObject();
			while(objeto != null) {
				listaPartidas.add((Partida)objeto);
				objeto = ois.readObject();
			}
			ois.close();
			
		} catch(EOFException e) {
			System.out.println("Fin de archivo.");
		}*/
		
		
		
		
		return listaPartidas;
	}
	
	
	public ArrayList<Jugador> getTop5Jugadores() throws ClassNotFoundException, IOException{

		//Cargamos las partidas guardadas
		ArrayList<Object> partidas = getListaPartidas();
		
		//ArrayList de jugadores
		ArrayList<Jugador> jugadores = new ArrayList<>();
		
		//Las leemos y guardamos cada uno de sus jugadores
		for(Object o: partidas) {
			Partida p = (Partida) o;
			for(Jugador j: p.getJugadores()) {
				jugadores.add(j);
			}
		}
		Comparator<Jugador> comp = Comparator.comparingInt(Jugador::getPuntaje).reversed();
		jugadores.sort(comp);
		
		return jugadores;

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
