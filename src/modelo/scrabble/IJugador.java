package modelo.scrabble;

import java.util.List;

public interface IJugador {

	String getNombre();

	void setNombre(String nombre);

	int getPuntaje();

	void setPuntaje(int puntaje);

	List<Character> getAtril();

	void setAtril(List<Character> atril);

}