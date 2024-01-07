package vista.scrabble.consolagrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import controlador.scrabble.Controlador;
import modelo.scrabble.Casillero;
import modelo.scrabble.Evento;
import modelo.scrabble.IJugador;
import modelo.scrabble.IPartida;
import modelo.scrabble.Jugador;
import vista.scrabble.Vista;
import javax.swing.JTextArea;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.awt.Rectangle;

public class ConsolaGrafica implements Vista{

	//Vista debe conocer a su Controlador
	private Controlador controlador;
	private String nombreJugador = "";
	private IJugador cliente;
	private JFrame frmScrabble;
	private JTextField entrada;
	private JButton intro;
	private Flujo flujoActual;
	private JPanel panelNorte;
	private JPanel panelOeste;
	private JPanel panelEste;
	private JScrollPane panelTerminal;
	private JTextArea terminal;
	
	
	//CONSTRUCTOR
	public ConsolaGrafica(Controlador controlador, String nombreJugador) {
		this.nombreJugador = nombreJugador.toUpperCase();
		this.controlador = controlador;
		controlador.setVista(this);
	}
	
	//Método general para procesar la entrada del usuario
	private void procesarEntrada(String entrada) {
		entrada = entrada.trim();
        if (entrada.isEmpty())
            return;
        mostrarMensaje("");
        flujoActual = flujoActual.elegirOpcion(entrada);
        flujoActual.mostarMenuTextual();
    }
	
	//Método para controlar los eventos del jugador del turno actual
	public boolean esTurnoActual() {
		IJugador jugadorTurnoActual = controlador.obtenerJugadores(controlador.obtenerTurnoActual());
		return cliente.equals(jugadorTurnoActual);
	}

	//Inicializa la ventana principal.
	public void iniciar() {
		inicializarVentana();
		frmScrabble.setVisible(true);
		this.cliente = controlador.agregarJugador(nombreJugador);
		flujoActual = new FlujoMenuPrincipal(this, controlador);
        flujoActual.mostarMenuTextual();
	}
	
	private void inicializarVentana() {
		
		frmScrabble = new JFrame();
		frmScrabble.setBounds(new Rectangle(250, 0, 0, 0));
		frmScrabble.getContentPane().setForeground(new Color(255, 255, 255));
		frmScrabble.getContentPane().setBackground(new Color(0, 0, 0));
		frmScrabble.setSize(700, 728);
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
				if(esTurnoActual()) {
					mostrarMensaje("Esperando al turno...");
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
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
		panelNorte.setPreferredSize(new Dimension(100,50));
		frmScrabble.getContentPane().add(panelNorte, BorderLayout.NORTH);
 
		panelOeste = new JPanel();
		panelOeste.setBackground(new Color(0, 0, 0));
		panelOeste.setPreferredSize(new Dimension(80,70));
		frmScrabble.getContentPane().add(panelOeste, BorderLayout.WEST);
 
		panelEste = new JPanel();
		panelEste.setBackground(new Color(0, 0, 0));
		panelEste.setPreferredSize(new Dimension(80,70));
		frmScrabble.getContentPane().add(panelEste, BorderLayout.EAST);
		
		panelTerminal = new JScrollPane();
		panelTerminal.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelTerminal.setBackground(new Color(0, 0, 0));
		frmScrabble.getContentPane().add(panelTerminal, BorderLayout.CENTER);
		
		terminal = new JTextArea();
		terminal.setBorder(new EmptyBorder(0, 0, 0, 0));
		terminal.setFont(new Font("JetBrains Mono", Font.PLAIN, 17));
		terminal.setEditable(false);
		terminal.setForeground(new Color(255, 255, 255));
		terminal.setBackground(new Color(0, 0, 0));
		panelTerminal.setViewportView(terminal);
		
	}
	
	//Métodos para imprimir por pantalla
	public void mostrarMensaje(String mensaje) {
	    terminal.append(mensaje + "\n");
	}
	
	public void mostrarComenzarPartida(ArrayList<IJugador> jugadores) {
		int turnoActual = controlador.obtenerTurnoActual();
		mostrarMensaje("Comienza la partida. Empieza el jugador " + jugadores.get(turnoActual).getNombre() + ".");
	}

	public void mostrarTablero(Casillero[][] tablero) {
		String obtenerTablero = "";
		for(int f = 0; f < tablero.length; f++) {
			for(int c = 0; c < tablero[f].length; c++) {
				obtenerTablero += tablero[f][c].getDescripcion() + " ";
			}
			obtenerTablero += "\n"; 
		}
		mostrarMensaje(obtenerTablero);
	}

	public void mostrarEstadoJugador(IJugador jugador) {
		String obtenerEstadoJugador = "Jugador: " + jugador.getNombre() + "\n"
				+ "ATRIL: " + jugador.getAtril() + "\n"
				+ "PUNTAJE: " + jugador.getPuntaje() + "\n";
		mostrarMensaje(obtenerEstadoJugador);
	}

	public void mostrarPartidasGuardadas(Object arg0) {
		ArrayList<IPartida> listaPartidas = new ArrayList<>();
		try {
			listaPartidas = controlador.obtenerPartidas();
			for(IPartida p: listaPartidas) {
				mostrarMensaje(p.toString());					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarPartidasGuardadas() {}

	public void mostrarRanking() {
		String dato = "";
		ArrayList<IJugador> top5Jugadores = new ArrayList<>();
		try {
			top5Jugadores = controlador.obtenerTop5Jugadores();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		mostrarMensaje("*** TOP 5 MEJORES JUGADORES ***");
		mostrarMensaje("");
		mostrarMensaje("Jugador		Puntaje\n");
		
		for(int i = 0; i < 5; i++) {
			dato += (i + 1 + ". ");
			if(i < top5Jugadores.size()) {
				IJugador jugador = top5Jugadores.get(i);
				if(jugador != null) {
					dato += jugador.getNombre() + ". ......... " + jugador.getPuntaje() + ".\n";				
					}
				else {
					dato += "......... .\n";
				}
			}
			else {
				dato += "......... .\n";
				}
			}
			mostrarMensaje(dato);
		}

	@Override
	public void mostrarPartidasGuardadas(ArrayList<IPartida> partidas) {
		// TODO Apéndice de método generado automáticamente
		
	}
	
	public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
		if(arg1 instanceof Evento) {
			switch ((Evento) arg1) {
			case NUEVOS_JUGADORES -> {
				mostrarMensaje("El usuario se ha conectado exitosamente.");				
				}
			case NUEVA_PARTIDA -> {
				ArrayList<Jugador> jugadores = modelo.getJugadores();
				mostrarComenzarPartida(obtenerJugadores());				
				}
			case PARTIDA_CARGADA -> {
				mostrarMensaje("Se ha cargado la partida exitosamente.");				
				}
			}
		}
	}
	
	
	}
	
	

