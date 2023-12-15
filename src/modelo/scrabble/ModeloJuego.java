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
	private Tablero tablero;
	// * BOLSA DE FICHAS: 
	private BolsaFichas bolsaDeFichas;
	// * JUGADORES:
	private Jugador[] jugadores; 
	// * TURNO:
	private int turnoActual;
	// * OTROS:
	private static Serializador serializador = new Serializador("PartidasGuardadas.bin");
	private ArrayList<Partida> partidas = new ArrayList<>();
	
	//INTERFAZ
	
	public void comenzarPartida(Jugador[] jugadores) throws RemoteException {
		
		//Comenzamos la primer partida
		tablero = new Tablero();
		bolsaDeFichas = new BolsaFichas();
		this.jugadores = new Jugador[4];
		turnoActual = -1;
		
		//Creamos y agregamos a los jugadores
		addJugadores(jugadores);
		
		tablero.comenzarPartida(jugadores, bolsaDeFichas);
		siguienteTurno();

		notificarObservadores(Evento.NUEVA_PARTIDA);
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
		
	}
	
	
	private void addJugadores(Jugador[] jugadores) throws RemoteException {
		//Agrego los jugadores
		for(int j = 0; j < jugadores.length; j++) {
			this.jugadores[j] = jugadores[j];
		}
		notificarObservadores(Evento.NUEVOS_JUGADORES);
	}
	
	
	public void devolverFichas(int idJugador, char[] fichasACambiar) throws RemoteException {
		
		//Devolvemos las fichas
		tablero.devolverFichas(jugadores, bolsaDeFichas, idJugador, fichasACambiar);
		siguienteTurno();
		
		notificarObservadores(Evento.CAMBIO_FICHAS);
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);

	} 
	
	
	public void agregarPalabra(int idJugador, int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException {
		
		//Agregamos la palabra
		tablero.addPalabra(jugadores, bolsaDeFichas, idJugador, x, y, palabraActual, horizontal);
		siguienteTurno();
		try {
			notificarObservadores(Evento.NUEVA_PALABRA);
			notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
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
		return turnoActual;
		
	}
	
	
	public void pasarTurno() throws RemoteException{
		siguienteTurno();
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
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
		int i = 0;
		boolean esPrimer = true;
		while(esPrimer && i < jugadores.length) {
			Jugador j = jugadores[i];
			if (j != null && j.getPuntaje() > 0) {
				esPrimer = false;
			}
			i++;
		}
		return esPrimer;
	}
	
	
	
	//Lectura de archivos
	
	public void cargarPartida(int idPartida) throws IOException, ClassNotFoundException{

		//Cargamos la lista de todas las partidas guardadas
		ArrayList<Partida> listaPartidas = getListaPartidas();
		
		//Alias del objeto
		Partida partidaACargar = (Partida) listaPartidas.get(idPartida);
		
		//Volcamos todo el contenido en el modelo
		tablero = partidaACargar.getTablero();
		bolsaDeFichas = partidaACargar.getBolsaDeFichas();
		jugadores = partidaACargar.getJugadores();
		turnoActual = partidaACargar.getTurnoActual();
		
		notificarObservadores(Evento.PARTIDA_CARGADA);
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
		
	}
	
	
	public void guardarPartida() throws IOException{
		
		Partida partidaActual = new Partida(tablero,bolsaDeFichas,jugadores,turnoActual);
		partidas.add(partidaActual);
		
		try {
            FileOutputStream fos = new FileOutputStream("PartidasGuardadas.bin");
            var oos = new ObjectOutputStream(fos);
            oos.writeObject(partidas);
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		
		notificarObservadores(Evento.PARTIDA_GUARDADA);
		
	}
	
	
	public ArrayList<Partida> getListaPartidas() throws IOException, ClassNotFoundException, RemoteException{

		try {
            FileInputStream fis = new FileInputStream("PartidasGuardadas.bin");
            var ois = new ObjectInputStream(fis);
            var listaPartidas = (ArrayList<Partida>) ois.readObject();
            fis.close();
            return listaPartidas;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
	}
	
	
	public ArrayList<Jugador> getTop5Jugadores() throws ClassNotFoundException, IOException{

		//Cargamos las partidas guardadas
		ArrayList<Partida> partidas = getListaPartidas();
		
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
	
	private void removeJugadores(int idUsuario) throws RemoteException {
		notificarObservadores(Evento.NUEVOS_JUGADORES);
	}
	
	public void notificarObservadores(Object obj) throws RemoteException {
		super.notificarObservadores(obj);
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
	
	

}
