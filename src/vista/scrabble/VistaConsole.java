package vista.scrabble;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaConsole {

	private JFrame frmScrabble;

	/**
	 * Lanza la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConsole ventanaPrincipal = new VistaConsole();
					ventanaPrincipal.frmScrabble.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicación. JButton enter = new JButton("Enter"); JTextField campoTexto = new JTextField("Escribir");
	 */
	public VistaConsole() {
		inicializarVentana();
	}

	/**
	 * Inicializa la ventana principal.
	 */
	private void inicializarVentana() {
		frmScrabble = new JFrame();
		frmScrabble.setResizable(true);
		frmScrabble.setPreferredSize(new Dimension(100,50));
		frmScrabble.setTitle("Scrabble");
		JPanel panelPrincipal = (JPanel) frmScrabble.getContentPane();
		panelPrincipal.setBackground(new Color(0, 0, 0));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		JPanel panelSurBoxLayout = new JPanel();
		panelSurBoxLayout.setSize(300, 300);
		JTextField campoTexto = new JTextField("Escribir");
		campoTexto.setFont(new Font("Segoe UI Variable Static Text", Font.PLAIN, 13));
		campoTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JButton enter = new JButton("Enter");
		enter.setFont(new Font("Segoe UI Variable Static Text", Font.PLAIN, 12));
		enter.setAlignmentX(Component.CENTER_ALIGNMENT);
		enter.setPreferredSize(new Dimension(100,50));
		panelPrincipal.add(panelSurBoxLayout,BorderLayout.SOUTH);
		panelSurBoxLayout.setLayout(new BoxLayout(panelSurBoxLayout, BoxLayout.X_AXIS));
		panelSurBoxLayout.add(campoTexto);
		panelSurBoxLayout.add(enter);
		frmScrabble.setBounds(100, 100, 706, 471);
		frmScrabble.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmScrabble.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Nueva partida");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu.setFont(new Font("Segoe UI Variable Static Text", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Reglas de juego");
		mnNewMenu_1.setFont(new Font("Segoe UI Variable Static Text", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Ayuda");
		mnNewMenu_2.setFont(new Font("Segoe UI Variable Static Text", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_2);
	}
		
}
