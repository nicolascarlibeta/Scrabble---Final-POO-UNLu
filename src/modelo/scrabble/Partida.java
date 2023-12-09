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
	private LocalDateTime fechaHora = LocalDateTime.now();
	private Tablero tablero;
	private BolsaFichas bolsaDeFichas;
	private Jugador[] jugadores = new Jugador[4];
	private int turnoActual = 0;
	
	public Partida(Tablero tablero, BolsaFichas bolsaDeFichas,
			Jugador[] jugadores, int turnoActual) {
		super();
		this.fechaHora.format(DateTimeFormatter.ofPattern("dd MM yyyy  HH:MM"));
		this.tablero = tablero;
		this.bolsaDeFichas = bolsaDeFichas;
		this.jugadores = jugadores;
		this.turnoActual = turnoActual;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	public void setFechaHora(LocalDateTime fechaHora) {
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
		String jug = "";
		for(Jugador j: jugadores) {
			jug += j.getNombre() + ", ";
		}
		return "ID: " + (id++) + "\n"
				+ "FECHA: " + fechaHora + "\n"
				+ "JUGADORES: " + jug + "\n"
				+ "TURNO ACTUAL: " + jugadores[turnoActual] + "\n";
	}
	
	

}
