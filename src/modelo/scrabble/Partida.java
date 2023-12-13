package modelo.scrabble;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Partida implements Serializable{
	
	private static final long serialVersionUID = -4243684796308041028L;
	private static int id = 1;
	private String fechaHora = "";
	private Tablero tablero;
	private BolsaFichas bolsaDeFichas;
	private Jugador[] jugadores = new Jugador[4];
	private int turnoActual = 0;
	private boolean terminada = false;
	
	public Partida(Tablero tablero, BolsaFichas bolsaDeFichas,
			Jugador[] jugadores, int turnoActual) {
		LocalDateTime fechaHoy = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
		this.id++;
		this.fechaHora = fechaHoy.format(formato);
		this.tablero = tablero;
		this.bolsaDeFichas = bolsaDeFichas;
		this.jugadores = jugadores;
		this.turnoActual = turnoActual;
	}
	public int getId() {
		return id;
	}
	
	public String getFechaHora() {
		return fechaHora;
	}
	
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
				
	public Tablero getTablero() {
		return tablero;
	}
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	public BolsaFichas getBolsaDeFichas() {
		return bolsaDeFichas;
	}
	public void setBolsaDeFichas(BolsaFichas bolsaDeFichas) {
		this.bolsaDeFichas = bolsaDeFichas;
	}
	public Jugador[] getJugadores() {
		return jugadores;
	}
	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}
	public int getTurnoActual() {
		return turnoActual;
	}
	public void setTurnoActual(int turnoActual) {
		this.turnoActual = turnoActual;
	}
	
	public String toString() {

		int j = 0;
		String jug = "";
		while(jugadores[j] != null) {
			jug += jugadores[j].getNombre() + ". ";
			j++;
		}
		return id
				+ ". FECHA: " + fechaHora + "\n"
				+ "JUGADORES: " + jug + "\n"
				+ "TURNO ACTUAL: " + jugadores[turnoActual].getNombre() + "\n";
	}
	
	

}
