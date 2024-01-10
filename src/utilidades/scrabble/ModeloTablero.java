package utilidades.scrabble;

import javax.swing.table.DefaultTableModel;
import modelo.scrabble.Casillero;
import modelo.scrabble.Letra;

public class ModeloTablero extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	// Constructor que toma una matriz de instancias de MiClase
    public ModeloTablero(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public Object getValueAt(int row, int column) {
    	
    	Casillero objetoCasillero = (Casillero) super.getValueAt(row, column);

        //Dependiendo de la columna, devuelve el valor del atributo correspondiente
        if(column >= 0 && column <= 15) {
        	return objetoCasillero.getDescripcion();
        }
        else {
        	return new Object();
        }
    }
    
   
    
}
