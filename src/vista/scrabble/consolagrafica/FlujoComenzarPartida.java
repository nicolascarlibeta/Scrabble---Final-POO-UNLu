package vista.scrabble.consolagrafica;

import controlador.scrabble.Controlador;

public class FlujoComenzarPartida extends Flujo{
	
	private String cadenaString = "";
	private int x;
	private int y;
	private EstadosPosibles estadoActual = EstadosPosibles.INGRESANDO_PALABRA;

	public FlujoComenzarPartida(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	public enum EstadosPosibles{
		INGRESANDO_PALABRA,
		INGRESANDO_COORDENADA_X,
		INGRESANDO_COORDENADA_Y
	}

	@Override
	public void mostarMenuTextual() {
		vista.println(mostrarTableroJuego());
		vista.println("Letras: " + controlador.obtenerAtril());
		vista.println("Puntaje: " + controlador.obtenerPuntaje());
		vista.println("");
		vista.println("");
		switch(estadoActual) {
		case INGRESANDO_PALABRA ->{
			vista.println("Ingresa una palabra:");
			}
		case INGRESANDO_COORDENADA_X ->{
			vista.println("Ingrese la coordenada x:");
			}
		case INGRESANDO_COORDENADA_Y ->{
			vista.println("Ingrese la coordenada y: ");
			}
		}
	}
	
	@Override
	public Flujo elegirOpcion(String opcion) {
		
		switch(estadoActual) {
		case INGRESANDO_PALABRA -> ingresarPalabra(opcion);
		case INGRESANDO_COORDENADA_X -> ingresarX(opcion);
		case INGRESANDO_COORDENADA_Y ->{
			return ingresarY(opcion);
		}
	}
		return this;
		
	}
	
	//INTERFAZ
	public String mostrarTableroJuego() {
		
		Object[][] tablero = controlador.obtenerTablero();
		String mostrarTablero = "";
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				mostrarTablero += tablero[f][c] + " ";			
			}
			mostrarTablero += "\n"; 
		}
		return mostrarTablero;
	}
	
	public void ingresarPalabra(String cadenaString) {
		this.cadenaString = cadenaString.toUpperCase();
		estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_X;
	}
	
	public void ingresarX(String x) {
		this.x = Integer.parseInt(x);
		estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_Y;
	}
	
	public Flujo ingresarY(String y) {
		this.y = Integer.parseInt(y);
		controlador.agregarPalabra(x,this.y,cadenaString,false);
		return new FlujoComenzarPartida(vista,controlador);
		//return new FlujoMenuPrincipal(vista,controlador);
	}
	

			
		/*
		public void mostrarAgregarJugadores() {
			
			mostrarMensaje("Ingrese el nombre del jugador: ");
			String nombreJugador = scanner().nextLine();
			controlador.agregarJugador(nombreJugador);
			
		}
		
		public void mostrarAgregarPalabra() {
			
			mostrarMensaje("Ingrese una palabra: ");
			String cadenaString = scanner().nextLine();
			mostrarMensaje("Ingrese la coordenada x: ");
			int x = scanner().nextInt();
			mostrarMensaje("Ingrese la coordenada y: ");
			int y = scanner().nextInt();
			controlador.agregarPalabra(x,y,cadenaString);
			
		}*/

}
