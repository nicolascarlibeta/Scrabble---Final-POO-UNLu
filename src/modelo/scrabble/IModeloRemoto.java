package modelo.scrabble;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IModeloRemoto extends IObservableRemoto{

	void comenzarPartida(Jugador[] jugadores) throws RemoteException;

	void devolverFichas(int idJugador, char[] fichasACambiar) throws RemoteException;

	void addPalabra(int idJugador, int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException;

	int siguienteTurno() throws RemoteException;

	void guardarPartida() throws IOException;

	//a revisar
	int getGanador() throws RemoteException;

	boolean isPrimerMovimiento() throws RemoteException;

	Ficha[][] getTablero() throws RemoteException;

	BolsaFichas getBolsaDeFichas() throws RemoteException;

	Jugador[] getJugadores() throws RemoteException;

	boolean isVacia() throws RemoteException;

	int getCantidadFichas() throws RemoteException;

	int getTurnoActual() throws RemoteException;
	
	void notificarObservadores(Object obj) throws RemoteException;
	
	ArrayList<Object> getListaPartidas() throws IOException, ClassNotFoundException, RemoteException;
	
	void cargarPartida(int idPartida) throws IOException, ClassNotFoundException, RemoteException;

}