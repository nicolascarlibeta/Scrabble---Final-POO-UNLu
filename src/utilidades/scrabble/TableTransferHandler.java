package utilidades.scrabble;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import modelo.scrabble.Casillero;

public class TableTransferHandler extends TransferHandler{
	
	private static final long serialVersionUID = 1L;


	public boolean canImport(TransferSupport support) {
        // Aceptar solo datos transferibles de tipo String
        return support.isDataFlavorSupported(new DataFlavor(Casillero.class,"Casillero"));
    }

 
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        JTable.DropLocation dropLocation = (JTable.DropLocation) support.getDropLocation();
        int row = dropLocation.getRow();
        int column = dropLocation.getColumn();

        try {
        	Casillero data = (Casillero) support.getTransferable().getTransferData(new DataFlavor(Casillero.class,"Casillero"));
            (((JTable) support.getComponent()).getModel()).setValueAt(data, row, column);
            return true;
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
