package modelo.scrabble;

import java.util.List;

public interface IJugador {

	int getId();

	String getNombre();

	void setNombre(String nombre);

	int getPuntaje();

	void setPuntaje(int puntaje);

	List<Letra> getAtril();

	void setAtril(List<Letra> atril);

}