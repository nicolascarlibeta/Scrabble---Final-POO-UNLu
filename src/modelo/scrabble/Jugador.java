package modelo.scrabble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Jugador implements Serializable, IJugador{
	
	private int id = new Random().nextInt(10000);
	private static final long serialVersionUID = -1267351262799502699L;
	private String nombre = "Jugador " + new Random().nextInt(600);
	private int puntaje = 0;
	private List<Letra> atril = new ArrayList<>();
	private boolean conectado = true;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public int getPuntaje() {
		return puntaje;
	}

	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	
	public List<Letra> getAtril() {
		return atril;
	}
	
	
	public void setAtril(List<Letra> atril) {
		this.atril = atril;
	}
	
	
	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	
	
	public boolean isConectado() {
		return conectado;
	}
	

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Jugador jugador = (Jugador) obj;
        return Objects.equals(id, jugador.id);
    }

}
