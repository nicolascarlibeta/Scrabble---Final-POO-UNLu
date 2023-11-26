package vista.scrabble.consolagrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

import controlador.scrabble.Controlador;
import flujos.scrabble.Flujo;
import flujos.scrabble.FlujoMenuPrincipal;
import vista.scrabble.Vista;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Scanner;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;

public class ConsolaGrafica implements Vista{

	//Vista debe conocer a Controlador
	private Controlador controlador;
	
	private JFrame frmScrabble;
	private JTextField entrada;
	private JButton intro;
	private Flujo flujoActual;
	private JTextArea terminal;
	private JPanel panelNorte;
	private JPanel panelOeste;
	private JPanel panelEste;
	private JPanel panelTerminal;
	
	
	//CONSTRUCTOR
	public ConsolaGrafica(Controlador controlador) {
		this.controlador = controlador;
		controlador.setVista(this);
		inicializarVentana();
	}
	
	//Métodos para imprimir por pantalla
	public void println(String texto) {
        terminal.append(texto + "\n");
    }
	
	//Limpiar la terminal
	public void limpiar() {
		terminal.setText("");
	}
	
	//Método principal (Menú Principal)
    public void mostrarMenuPrincipal() {
        flujoActual = new FlujoMenuPrincipal(this, controlador);
        flujoActual.mostarMenuTextual();
    }
	
	//Método general para procesar la entrada del usuario
	private void procesarEntrada(String entrada) {
		entrada = entrada.trim();
        if (entrada.isEmpty())
            return;
        limpiar();
        flujoActual = flujoActual.elegirOpcion(entrada);
        flujoActual.mostarMenuTextual();
    }

	//Inicializa la ventana principal.
	private void inicializarVentana() {
		
		frmScrabble = new JFrame();
		frmScrabble.getContentPane().setForeground(new Color(255, 255, 255));
		frmScrabble.getContentPane().setBackground(new Color(0, 0, 0));
		frmScrabble.setSize(1366, 728);
		frmScrabble.setVisible(true);
		frmScrabble.setResizable(true);
		frmScrabble.setTitle("Scrabble");
		
		JPanel panelPrincipal = (JPanel)frmScrabble.getContentPane();
		panelPrincipal.setLayout(new BorderLayout());
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(255, 255, 255));
		panelPrincipal.add(panelSur,BorderLayout.SOUTH);
		panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.X_AXIS));
		
		entrada = new JTextField();
		entrada.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//Recibe la entrada (opcion) y la procesa
					procesarEntrada(entrada.getText());
					//Setea en vacio el campo de entrada
					entrada.setText("");
				}
			}
		});
		entrada.setForeground(new Color(255, 255, 255));
		entrada.setBackground(new Color(0, 0, 0));
		entrada.setFont(new Font("Segoe UI Variable Static Text", Font.PLAIN, 13));
		intro = new JButton();
		intro.setForeground(new Color(255, 255, 255));
		intro.setBackground(new Color(0, 0, 0));
		intro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Recibe la entrada (opcion) y la procesa
				procesarEntrada(entrada.getText());
				//Setea en vacio el campo de entrada
				entrada.setText("");
			}
		});
		intro.setFont(new Font("Segoe UI Variable Static Text", Font.PLAIN, 13));
		intro.setText("Intro");
		
		panelSur.add(entrada); panelSur.add(intro);
 
		panelNorte = new JPanel();
		panelNorte.setBackground(new Color(0, 0, 0));
		panelNorte.setPreferredSize(new Dimension(100,70));
		frmScrabble.getContentPane().add(panelNorte, BorderLayout.NORTH);
 
		panelOeste = new JPanel();
		panelOeste.setBackground(new Color(0, 0, 0));
		panelOeste.setPreferredSize(new Dimension(80,70));
		frmScrabble.getContentPane().add(panelOeste, BorderLayout.WEST);
 
		panelEste = new JPanel();
		panelEste.setBackground(new Color(0, 0, 0));
		panelEste.setPreferredSize(new Dimension(80,70));
		frmScrabble.getContentPane().add(panelEste, BorderLayout.EAST);
		
		terminal = new JTextArea();
		terminal.setEditable(false);
		terminal.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
		terminal.setForeground(new Color(255, 255, 255));
		terminal.setBackground(new Color(0, 0, 0));
		panelTerminal = new JPanel();
		panelTerminal.setForeground(new Color(255, 255, 255));
		panelTerminal.setBackground(new Color(0, 0, 0));
		panelTerminal.setLayout(new FlowLayout());
		panelTerminal.add(terminal);
		frmScrabble.getContentPane().add(panelTerminal);
		
		mostrarMenuPrincipal();
		
	}

	@Override
	public void mostrarIngresarJugadores() {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void mostrarComenzarPartida() {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void mostrarTablero() {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void mostrarOpcionesJuego() {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void mostrarVerReglas() {
		// TODO Apéndice de método generado automáticamente
		
	}

	
	
		
}
