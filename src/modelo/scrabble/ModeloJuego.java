package modelo.scrabble;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.io.*;

public class ModeloJuego extends ObservableRemoto implements IModeloRemoto, Serializable {
	
	private static final long serialVersionUID = 7507099928370303646L;
	private Tablero tablero;
	private BolsaFichas bolsaDeFichas;
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Partida> partidas = new ArrayList<>();
	private int turnoActual;
	
	//INTERFAZ
	
	public void addJugador(Jugador jugador) throws RemoteException {
		conectarJugador(jugador);
	}
	
	public void comenzarPartida() throws RemoteException {
		
		//Comenzamos la primer partida
		tablero = new Tablero(this);
		bolsaDeFichas = new BolsaFichas();
		turnoActual = -1;

		tablero.comenzarPartida(jugadores, bolsaDeFichas);
		siguienteTurno();

		notificarObservadores(Evento.NUEVA_PARTIDA);
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
		
	}
	
	
	public boolean agregarPalabra(String x, String y, Palabra palabraActual, String disposicion) throws IOException {
		
		// Referencio al jugador actual
		Jugador jugadorActual = jugadores.get(getTurnoActual());
		
		// Hago un alias del conjunto de letras de la palabra
        List<Letra> letrasPalabra = palabraActual.getLetras();
		
		//Primero valido que la palabra contenga letras del atril
		for(Letra l: letrasPalabra) {
			if(!jugadorActual.getAtril().contains(l)) {
				notificarObservadores(Evento.ERROR_ATRIL);
				return false;
			}
		}
		
		//Segundo, valido la palabra en el diccionario
		if(!new Diccionario().contieneA(palabraActual.getPalabra().toLowerCase())) {
			notificarObservadores(Evento.ERROR_DICCIONARIO);
			return false;
		}
	
		//Valido las coordenadas X e Y
		int X = 72, Y = 72;
		if(!isPrimerMovimiento()) {
			X = (int) x.toCharArray()[0];
			Y = (int) y.toCharArray()[0];			
		
		if((X < 65 || X > 79) || (Y < 65 || Y > 79)) {
			notificarObservadores(Evento.ERROR_COORDENADAS);
			return false;
			}
		}
		
		//Valido las disposición
		boolean horizontal = false;
		switch(disposicion) {
		case "1" -> horizontal = true;
		case "2" -> horizontal = false;
		default -> {
			notificarObservadores(Evento.ERROR_DISPOSICION);
			return false;
		}
		}
		
		if(!isPrimerMovimiento() && !validarPalabra(X, Y, palabraActual, horizontal)) {
			notificarObservadores(Evento.ERROR_VALIDACION_PALABRA);
			return false;
			}
		
		
		//Agregamos la palabra
		tablero.addPalabra(bolsaDeFichas, jugadorActual, X, Y, palabraActual, horizontal);
		siguienteTurno();
		try {
			notificarObservadores(Evento.NUEVA_PALABRA);
			notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean validarPalabra(int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException{
		return tablero.validarPalabra(x, y, palabraActual, horizontal, isPrimerMovimiento());
	}
	
	
	public boolean cambiarFichas(char[] fichasACambiar) throws RemoteException {
		
		//Referencio al jugador actual
		Jugador jugadorActual = jugadores.get(turnoActual);
		
		//Devolvemos las fichas
		return tablero.cambiarFichas(bolsaDeFichas, jugadorActual, fichasACambiar);

	} 
	
	
	public void siguienteTurno() throws RemoteException{
		
		int cantidadFichas = 0;
		for(Jugador j: jugadores) {
			cantidadFichas += j.getAtril().size();
		}
		
		if(cantidadFichas == 0 && 
				bolsaDeFichas.getCantidadFichas() == 0) {
			notificarObservadores(Evento.FIN_PARTIDA);
			return;
		}
		
		if(++this.turnoActual < jugadores.size()) {
			return;
		}
		else {
			this.turnoActual = 0;
		}
		
	}
	
	
	public void pasarTurno() throws RemoteException{
		siguienteTurno();
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
	}
	
	
	public int obtenerGanador() throws RemoteException{
		int mayor = jugadores.get(0).getPuntaje();
		int idGanador = 0;
		for(int j = 1; j < jugadores.size(); j++) {
			if(jugadores.get(j).getPuntaje() > mayor) {
				mayor = jugadores.get(j).getPuntaje();
				idGanador = j;
			}
		}
		return idGanador;
	}
	
	
	public boolean isPrimerMovimiento() throws RemoteException{
		int i = 0;
		boolean esPrimer = true;
		while(esPrimer && i < jugadores.size()) {
			Jugador j = jugadores.get(i);
			if (j != null && j.getPuntaje() > 0) {
				esPrimer = false;
			}
			i++;
		}
		return esPrimer;
	}
	
	
	
	//Lectura de archivos
	
	public void cargarPartida(int idPartida) throws IOException, ClassNotFoundException, RemoteException{

		if(idPartida < 1 || idPartida > partidas.size()) {
			return;
		}
		
		//Cargamos la lista de todas las partidas guardadas
		ArrayList<Partida> listaPartidas = getListaPartidas();
		
		//Alias del objeto
		Partida partidaACargar = listaPartidas.get(idPartida - 1);
		
		//Volcamos todo el contenido en el modelo
		tablero = partidaACargar.getTablero();
		bolsaDeFichas = partidaACargar.getBolsaDeFichas();
		jugadores = partidaACargar.getJugadores();
		turnoActual = partidaACargar.getTurnoActual();
		
		notificarObservadores(Evento.PARTIDA_CARGADA);
		notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
		
	}
	
	
	public void guardarPartida() throws IOException{
		
		if(this.getCantidadJugadores() != 0) {
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
		for(Partida p: partidas) {
			ArrayList<Jugador> jug = p.getJugadores();
			for(Jugador j: jug) {
				if(j != null) {
					jugadores.add(j);					
				}
			}
		}
		Comparator<Jugador> comp = Comparator.comparing(Jugador::getPuntaje).reversed();
		jugadores.sort(comp);
		
		return jugadores;

	}
	
	
	//Metodos de observer
	
	public void notificarObservadores(Object obj) throws RemoteException {
		super.notificarObservadores(obj);
	}
	
	
	//Metodos del modelo
	
	public Jugador obtenerJugadorGanador() throws RemoteException{
		ArrayList<Jugador> jugadores = new ArrayList<>();
		Comparator<Jugador> comp = Comparator.comparing(Jugador::getPuntaje).reversed();
		jugadores.sort(comp);
		return jugadores.get(0); 
	}
	
	private void conectarJugador(Jugador nuevoJugador) throws RemoteException {
		jugadores.add(nuevoJugador);
		notificarObservadores(Evento.NUEVO_JUGADOR);
	}
	
	
	public Jugador desconectarJugador(Jugador jugador) throws RemoteException {
		if(this.jugadores.size() > 1) {
			jugador.setConectado(false);
			this.jugadores.remove(jugador);
			notificarObservadores(Evento.JUGADOR_DESCONECTADO);
			notificarObservadores(Evento.CAMBIO_ESTADO_PARTIDA);
		}
		return jugador;
	}
	
	
	//Setters y Getters
	
	public Casillero[][] getTablero() throws RemoteException{
		return tablero.getTablero();
	}

	public int getTurnoActual() throws RemoteException{
		return turnoActual;
	}
	
	public BolsaFichas getBolsaDeFichas() throws RemoteException{
		return bolsaDeFichas;
	}
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public boolean isVacia() throws RemoteException{
		return bolsaDeFichas.getCantidadFichas() == 0;
	}
	
	public int getCantidadFichas() throws RemoteException{
		return bolsaDeFichas.getCantidadFichas();
	}
	
	public int getCantidadJugadores() throws RemoteException{
		return jugadores.size();
	}


		
	

}
