package modelo.scrabble;

import java.util.*;
import java.io.*;

public class Diccionario {

	private static File palabras;
	
    //INTERFAZ
	
	public static void porIndice(int primeraLetra) {
		
		if(primeraLetra >= 65 && primeraLetra <= 70) {
    		palabras = new File("A-F.txt");
    	}
    	else if(primeraLetra >= 71 && primeraLetra <= 77) {
    		palabras = new File("G-M.txt");
    	}
    	else if(primeraLetra >= 78 && primeraLetra <= 83) {
    		palabras = new File("N-S.txt");
    	}
    	else {
    		palabras = new File("T-Z.txt");
    	}
	}
    
    public static boolean contieneA(String palabraBuscada) throws IOException{
    	
    	//Tomo la primera letra de la palabra a buscar
    	int primeraLetra = (int) palabraBuscada.toCharArray()[0];
    	
    	//La construyo por el indice
    	porIndice(primeraLetra);
  
    	//Busco la palabra
    	boolean esta = false;
    	BufferedReader diccionario = new BufferedReader(new FileReader(palabras));
    	String linea = diccionario.readLine();
    	if(linea.equals(palabraBuscada)) {
			esta = true;
		}
    	else {
    		while(linea != null && !esta) {
        		if(linea.equals(palabraBuscada)) {
        			esta = true;
        		}
        		linea = diccionario.readLine();
        	}
    	}
    	diccionario.close();
    	return esta;
    }

   
	
}
