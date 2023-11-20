package vista.scrabble.consolagrafica;

import controlador.scrabble.Controlador;

public abstract class FlujoJugador extends Flujo{
	
	public FlujoJugador(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	private String cadenaString = "";
	private boolean horizontal;
	private int x;
	private int y;
	private EstadosPosibles estadoActual = EstadosPosibles.INGRESANDO_PALABRA;
	
	public enum EstadosPosibles{
		INGRESANDO_PALABRA,
		INGRESANDO_DISPOSICION,
		INGRESANDO_COORDENADA_X,
		INGRESANDO_COORDENADA_Y
	}

	public void mostarMenuTextual() {
		vista.println(mostrarTableroJuego());
		vista.println("Letras: " + controlador.obtenerAtril());
		vista.println("Puntaje: " + controlador.obtenerPuntaje());
		vista.println("");
		//vista.println("");
		switch(estadoActual) {
		case INGRESANDO_PALABRA -> vista.println("Ingrese una palabra:");
		case INGRESANDO_DISPOSICION -> {
			vista.println("¿De que forma quiere agregar esta palabra?:");
			vista.println("1. Horizontal \t 2. Vertical:");
		}
		case INGRESANDO_COORDENADA_X -> vista.println("Ingrese la coordenada HORIZONTAL (A, B, C, D, E, F, ...):");
		case INGRESANDO_COORDENADA_Y -> vista.println("Ingrese la coordenada VERTICAL (1, 2, 3, 4, 5, 6, ...): ");
		}
	}

	public Flujo elegirOpcion(String opcion) {
		
		switch(estadoActual) {
			case INGRESANDO_PALABRA -> ingresarPalabra(opcion);
			case INGRESANDO_DISPOSICION -> ingresarDisposicion(opcion);
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
		estadoActual = EstadosPosibles.INGRESANDO_DISPOSICION;
	}
	
	public void ingresarDisposicion(String opcion) {
		if(opcion.equals("1")) {
			horizontal = true;
		}
		else {
			horizontal = false;
		}
		estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_X;
	}
	
	public void ingresarX(String x) {
		x = x.toUpperCase();
		this.x = (int) x.toCharArray()[0];
		estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_Y;
	}
	
	public Flujo ingresarY(String y) {
		int idJugador = 1;
		this.y = Integer.parseInt(y);
		controlador.agregarPalabra(idJugador,x,this.y,cadenaString,horizontal);
		return avanzarFlujo();
	}
	
	public abstract Flujo avanzarFlujo();

}
