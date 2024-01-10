package utilidades.scrabble;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.TransferHandler;

public class TableTransferHandler extends TransferHandler{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public boolean canImport(TransferSupport support) {
        // Aceptar solo datos transferibles de tipo String
        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        JTable.DropLocation dropLocation = (JTable.DropLocation) support.getDropLocation();
        int row = dropLocation.getRow();
        int column = dropLocation.getColumn();
        String data = null;
		try {
			data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ((ModeloTablero) ((JTable) support.getComponent()).getModel()).setValueAt(data, row, column);
        return true;
       
    }

}
