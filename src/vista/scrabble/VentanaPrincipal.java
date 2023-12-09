package vista.scrabble;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controlador.scrabble.Controlador;
import modelo.scrabble.Ficha;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.FlujoIngresarJugadores;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal implements Ventana {

	private JFrame frmScrabble;
	private JMenuItem iNuevaPartida;
	
	//CONSTRUCTOR
	public VentanaPrincipal() {
		inicializarVentana();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void inicializarVentana() {

		frmScrabble = new JFrame();
		frmScrabble.setResizable(false);
		frmScrabble.setTitle("Scrabble");
		frmScrabble.setBounds(100, 100, 517, 346);
		frmScrabble.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScrabble.getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		frmScrabble.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		frmScrabble.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace\\Scrabble\\Scrabble-Logo.png"));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		frmScrabble.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		frmScrabble.getContentPane().add(panel_3);
		
		JMenuBar menuBar = new JMenuBar();
		frmScrabble.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Jugar a Scrabble");
		menuBar.add(mnNewMenu);
		
		iNuevaPartida = new JMenuItem("Nueva partida");
		mnNewMenu.add(iNuevaPartida);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cargar partida");
		mnNewMenu.add(mntmNewMenuItem_1);
	}

	public void setVisible(boolean b) {
		frmScrabble.setVisible(b);
	}
	
	public int validarCantidadJugadores(String entrada) {
		try {
			int cantidadJugadores = Integer.parseInt(entrada);
			return cantidadJugadores;
		} catch(NumberFormatException e) {
			return -1;
		}
	}
	
	public void nuevaPartida(ActionListener accion) {
		iNuevaPartida.addActionListener(accion);
	}
	
	public JFrame parentComponent() {
		return frmScrabble;
	}



	
}
