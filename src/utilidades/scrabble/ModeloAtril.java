package utilidades.scrabble;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.scrabble.Casillero;
import modelo.scrabble.Letra;

public class ModeloAtril extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private Object[][] datos; 
    private String[] columnas = {"Columna1"};

    public ModeloAtril(List<Letra> datos) {
    	int c = 0;
    	this.datos = new Object[1][datos.size()];
    	for(Letra l: datos) {
    		this.datos[0][c] = l;
    	}
    }

    public int getRowCount() {
        return datos.length;
    }

    public int getColumnCount() {
        return columnas.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
        Casillero objetoLetra = (Casillero) datos[0][rowIndex];

        // Dependiendo de la columna, devuelve el valor del atributo correspondiente
        if(columnIndex >= 0 && columnIndex <= 6) {
        	return objetoLetra.getDescripcion();
        }
        else {
        	return new Object();
        }
    }

	
    
}
