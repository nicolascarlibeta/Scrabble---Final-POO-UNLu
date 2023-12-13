package utilidades.scrabble;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.scrabble.Jugador;

public class Serializador {
	private String fileName;

	public Serializador(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean writeOneObject(Object obj) {
		boolean respuesta = false;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(obj);
			oos.close();
			respuesta = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	
	public boolean addOneObject(Object obj) {
		boolean respuesta = false;
		try {
			AddableObjectOutputStream oos = new AddableObjectOutputStream (new FileOutputStream(fileName,true));
			oos.writeObject(obj);
			oos.close();
			respuesta = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	
	
	public Object readFirstObject() {
		Object respuesta = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(fileName));
			
			respuesta = ois.readObject();
			
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	
	
	public ArrayList<Object> readObjects() {
	    ArrayList<Object> listOfObject = new ArrayList<>();
	    try {
	        ObjectInputStream ois = new ObjectInputStream(
	                new FileInputStream(fileName));

	        try {
	            while (true) {
	                Object r = ois.readObject();
	                listOfObject.add(r);
	            }
	        } catch (EOFException e) {
	            // Se lanza EOFException cuando llega al final del archivo
	            System.out.println("Lectura completada");
	        }

	        ois.close();

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();

	    } catch (IOException e) {
	        e.printStackTrace();

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return listOfObject;
	}
	

}
