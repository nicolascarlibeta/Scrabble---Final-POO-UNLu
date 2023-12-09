package modelo.scrabble;

import java.io.Serializable;
import java.util.HashMap;

public class BolsaFichas implements Serializable{
	
	private HashMap<Character, Integer> bolsaDeFichas = new HashMap<>();
	private int cantidadFichas = 100;
	
	public BolsaFichas() {
		
		//BOLSA DE FICHAS.
		bolsaDeFichas.put('A', 12); bolsaDeFichas.put('B', 2); bolsaDeFichas.put('C', 4); 
		bolsaDeFichas.put('D', 5); bolsaDeFichas.put('E', 12); bolsaDeFichas.put('F', 1); bolsaDeFichas.put('G', 2);
		bolsaDeFichas.put('H', 2); bolsaDeFichas.put('I', 6); bolsaDeFichas.put('J', 1); bolsaDeFichas.put('L', 4);
		bolsaDeFichas.put('M', 2); bolsaDeFichas.put('N', 5); bolsaDeFichas.put('Ñ', 1);
		bolsaDeFichas.put('O', 9); bolsaDeFichas.put('P', 2); bolsaDeFichas.put('Q', 1); bolsaDeFichas.put('R', 5);
		bolsaDeFichas.put('S', 6); bolsaDeFichas.put('T', 4); bolsaDeFichas.put('U', 5);
		bolsaDeFichas.put('V', 1); bolsaDeFichas.put('X', 1); bolsaDeFichas.put('Y', 1); bolsaDeFichas.put('Z', 1);
		bolsaDeFichas.put(' ', 2);
	
	}
	
	public int getCantidadFichas() {
		return cantidadFichas;
	}
	
	public void setCantidadFichas(int cantidadFichas) {
		this.cantidadFichas = cantidadFichas;
	}

	public int get(char letra) {
		try {
			return bolsaDeFichas.get(letra);			
		}
		catch(NullPointerException e) {
			return -1;
		}
	}

	public void put(char letra, int cantidad) {
		bolsaDeFichas.put(letra, cantidad);
	}

}
