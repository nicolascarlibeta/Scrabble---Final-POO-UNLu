package vista.scrabble;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class VentanaPartidas {

	private JFrame frame;

	
	public VentanaPartidas() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setVisible(boolean b) {
		//frmScrabble.setVisible(b);
		
	}

}
