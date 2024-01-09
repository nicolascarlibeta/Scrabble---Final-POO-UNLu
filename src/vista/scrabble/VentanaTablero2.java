package vista.scrabble;

import javax.swing.JFrame;
import modelo.scrabble.IJugador;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class VentanaTablero2 implements Ventana{

	private JFrame frmScrabble;
	private IJugador cliente;
	private JLabel menuPrincipal;
	private JLabel panelNotificaciones; 
	private JButton comenzarPartida;
	private ImageIcon scrabble;
	private JTextField palabra;
	private JTextArea tablero;
	private JLabel jugador;
	private JLabel turnoDe;
	private JLabel puntaje;
	private JLabel cantFichas;
	private JLabel cntFichas;
	private JButton enviarPalabra;
	private JButton cambioFichas;
	private JButton pasarTurno;
	private JButton desconectar; 
	private JRadioButton horizontal;
	private JRadioButton vertical;
	private JTextField coorX;
	private JTextField coorY;
	private JTextField cadenaFichas;
	
	
	//CONSTRUCTOR
	public VentanaTablero2() {
		inicializarVentana();
	}
	
	public void setCliente(IJugador cliente) {
		this.cliente = cliente;
		jugador.setText(cliente.getNombre());
	}
	
	//Inicializa la ventana principal.
	private void inicializarVentana() {
		frmScrabble = new JFrame();
		frmScrabble.setResizable(false);
		frmScrabble.setTitle("Scrabble");
		frmScrabble.setBounds(100, 100, 710, 640);
		frmScrabble.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(null);
		panelNorte.setBackground(new Color(0, 128, 0));
		panelNorte.setPreferredSize(new Dimension(100, 60));
		frmScrabble.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_55 = new JPanel();
		panel_55.setBackground(new Color(0, 128, 0));
		panelNorte.add(panel_55);
		panel_55.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelNotificaciones = new JLabel("");
		panelNotificaciones.setForeground(new Color(255, 255, 255));
		panelNotificaciones.setBackground(new Color(0, 128, 0));
		panelNotificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		panel_55.add(panelNotificaciones);
		
		scrabble = new ImageIcon("Scrabble-Logo.png");
		
		
		JPanel panelOeste = new JPanel();
		panelOeste.setBackground(new Color(0, 128, 0));
		frmScrabble.getContentPane().add(panelOeste, BorderLayout.WEST);
		panelOeste.setPreferredSize(new Dimension(140, 100));
		panelOeste.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_39 = new JPanel();
		panel_39.setBackground(new Color(0, 128, 0));
		panelOeste.add(panel_39);
		
		JPanel panel_40 = new JPanel();
		panelOeste.add(panel_40);
		panel_40.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_42 = new JPanel();
		panel_40.add(panel_42);
		panel_42.setLayout(new GridLayout(0, 1, 0, 0));
		
		cantFichas = new JLabel("Cant. Fichas:");
		cantFichas.setHorizontalAlignment(SwingConstants.CENTER);
		panel_42.add(cantFichas);
		
		JPanel panel_41 = new JPanel();
		panel_40.add(panel_41);
		
		cntFichas = new JLabel("");
		panel_41.add(cntFichas);
		
		JPanel panelEste = new JPanel();
		panelEste.setBorder(null);
		panelEste.setBackground(new Color(0, 128, 0));
		panelEste.setPreferredSize(new Dimension(210, 100));
		frmScrabble.getContentPane().add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panelEste.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 128, 0));
		panel_7.add(panel_9);
		panel_9.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_38 = new JPanel();
		panel_38.setBackground(new Color(0, 128, 0));
		panel_9.add(panel_38);
		
		JLabel lblNewLabel_9 = new JLabel("Jugador:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		panel_38.add(lblNewLabel_9);
		
		jugador = new JLabel("");
		jugador.setForeground(new Color(255, 255, 255));
		panel_38.add(jugador);
		
		JPanel panel_37 = new JPanel();
		panel_37.setBackground(new Color(0, 128, 0));
		panel_9.add(panel_37);
		
		desconectar = new JButton("Desconectar");
		panel_37.add(desconectar);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_7.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_8.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Turno de:");
		panel_3.add(lblNewLabel);
		
		turnoDe = new JLabel("");
		panel_3.add(turnoDe);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_8.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Puntaje:");
		panel_4.add(lblNewLabel_1);
		
		puntaje = new JLabel("");
		panel_4.add(puntaje);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 128, 0));
		panelEste.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(0, 128, 0));
		panel_6.add(panel_11);
		panel_11.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_11.add(panel_1);
		
		JLabel lblNewLabel_7 = new JLabel("Atril:");
		panel_1.add(lblNewLabel_7);
		
		JPanel panel = new JPanel();
		panel_11.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_56 = new JPanel();
		panel.add(panel_56, BorderLayout.CENTER);
		panel_56.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_43 = new JPanel();
		panel_56.add(panel_43, BorderLayout.NORTH);
		
		JPanel panel_44 = new JPanel();
		panel_56.add(panel_44, BorderLayout.SOUTH);
		
		JPanel panel_57 = new JPanel();
		panel_56.add(panel_57, BorderLayout.EAST);
		
		JPanel panel_58 = new JPanel();
		panel_56.add(panel_58, BorderLayout.WEST);
		
		JPanel panel_59 = new JPanel();
		panel_56.add(panel_59, BorderLayout.CENTER);
		panel_59.setLayout(new GridLayout(0, 7, 0, 0));
		
		JPanel panel_60 = new JPanel();
		panel_59.add(panel_60);
		
		JPanel panel_61 = new JPanel();
		panel_59.add(panel_61);
		
		JPanel panel_62 = new JPanel();
		panel_59.add(panel_62);
		
		JPanel panel_63 = new JPanel();
		panel_59.add(panel_63);
		
		JPanel panel_64 = new JPanel();
		panel_59.add(panel_64);
		
		JPanel panel_65 = new JPanel();
		panel_59.add(panel_65);
		
		JPanel panel_66 = new JPanel();
		panel_59.add(panel_66);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(0, 128, 0));
		panel_6.add(panel_10);
		
		JLabel lblNewLabel_2 = new JLabel("Elija una opción:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		panel_10.add(lblNewLabel_2);
		
		JPanel panelSur = new JPanel();
		panelSur.setBackground(new Color(0, 128, 0));
		panelSur.setPreferredSize(new Dimension(100, 170));
		frmScrabble.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEspaciado = new JPanel();
		panelEspaciado.setBackground(new Color(0, 128, 0));
		panelEspaciado.setPreferredSize(new Dimension(10, 20));
		panelSur.add(panelEspaciado, BorderLayout.NORTH);
		
		JPanel panelTabbed = new JPanel();
		panelSur.add(panelTabbed, BorderLayout.CENTER);
		panelTabbed.setLayout(new CardLayout(0, 0));
		
		JTabbedPane PanelSubMenu = new JTabbedPane(JTabbedPane.TOP);
		panelTabbed.add(PanelSubMenu, "name_19720607695600");
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 20));
		PanelSubMenu.addTab("Agregar palabra", null, panel_2, null);
		panel_2.setLayout(new GridLayout(0, 6, 0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13);
		panel_13.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_22 = new JPanel();
		panel_13.add(panel_22);
		panel_22.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Ingresar palabra:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(lblNewLabel_3);
		
		JPanel panel_21 = new JPanel();
		panel_13.add(panel_21);
		
		palabra = new JTextField();
		panel_21.add(palabra);
		palabra.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		panel_2.add(panel_14);
		panel_14.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_24 = new JPanel();
		panel_14.add(panel_24);
		panel_24.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Coordenada X:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_24.add(lblNewLabel_4);
		
		JPanel panel_23 = new JPanel();
		panel_14.add(panel_23);
		
		coorX = new JTextField();
		panel_23.add(coorX);
		coorX.setColumns(10);
		
		JPanel panel_15 = new JPanel();
		panel_2.add(panel_15);
		panel_15.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_26 = new JPanel();
		panel_15.add(panel_26);
		panel_26.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Coordenada Y:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_26.add(lblNewLabel_5);
		
		JPanel panel_25 = new JPanel();
		panel_15.add(panel_25);
		
		coorY = new JTextField();
		panel_25.add(coorY);
		coorY.setColumns(10);
		
		JPanel panel_16 = new JPanel();
		panel_2.add(panel_16);
		panel_16.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_28 = new JPanel();
		panel_16.add(panel_28);
		panel_28.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Elegir disposición:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_28.add(lblNewLabel_6);
		
		JPanel panel_27 = new JPanel();
		panel_16.add(panel_27);
		panel_27.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_31 = new JPanel();
		panel_27.add(panel_31);
		panel_31.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_33 = new JPanel();
		panel_31.add(panel_33);
		
		horizontal = new JRadioButton("Horizontal");
		vertical = new JRadioButton("Vertical");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(horizontal);
		buttonGroup.add(vertical);
		
		JPanel panel_32 = new JPanel();
		panel_31.add(panel_32);
		
		panel_33.add(horizontal);
		panel_32.add(vertical);
		
		JPanel panel_17 = new JPanel();
		panel_2.add(panel_17);
		panel_17.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_30 = new JPanel();
		panel_17.add(panel_30);
		
		JPanel panel_29 = new JPanel();
		panel_17.add(panel_29);
		
		enviarPalabra = new JButton("Enviar");
		panel_29.add(enviarPalabra);
		
		JPanel panel_18 = new JPanel();
		panel_2.add(panel_18);
		panel_18.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_19 = new JPanel();
		panel_18.add(panel_19);
		
		JPanel panel_20 = new JPanel();
		panel_18.add(panel_20);
		
		JPanel panel_5 = new JPanel();
		PanelSubMenu.addTab("Cambiar fichas", null, panel_5, null);
		panel_5.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_35 = new JPanel();
		panel_5.add(panel_35);
		panel_35.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel leyendaCambiarFichas = new JLabel("Ingrese una palabra que contenga las letras que desea cambiar (en cualquier orden):");
		leyendaCambiarFichas.setHorizontalAlignment(SwingConstants.CENTER);
		panel_35.add(leyendaCambiarFichas);
		
		JPanel panel_36 = new JPanel();
		panel_5.add(panel_36);
		
		cadenaFichas = new JTextField();
		panel_36.add(cadenaFichas);
		cadenaFichas.setColumns(10);
		
		JPanel panel_34 = new JPanel();
		panel_5.add(panel_34);
		
		cambioFichas = new JButton("Cambiar fichas");
		panel_34.add(cambioFichas);
		
		JPanel panel_12 = new JPanel();
		PanelSubMenu.addTab("Pasar turno", null, panel_12, null);
		panel_12.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_45 = new JPanel();
		panel_12.add(panel_45);
		panel_45.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_49 = new JPanel();
		panel_45.add(panel_49);
		panel_49.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("Presione para pasar el turno:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_49.add(lblNewLabel_8);
		
		JPanel panel_51 = new JPanel();
		panel_45.add(panel_51);
		
		pasarTurno = new JButton("Pasar turno");
		panel_51.add(pasarTurno);
		
		JPanel panel_50 = new JPanel();
		panel_45.add(panel_50);
		
		JPanel panel_46 = new JPanel();
		panel_12.add(panel_46);
		panel_46.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_53 = new JPanel();
		panel_46.add(panel_53);
		
		JPanel panel_54 = new JPanel();
		panel_46.add(panel_54);
		
		JPanel panel_52 = new JPanel();
		panel_46.add(panel_52);
		
		JPanel panel_47 = new JPanel();
		panel_12.add(panel_47);
		
		JPanel panel_48 = new JPanel();
		panel_12.add(panel_48);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		panelCentro.setFont(new Font("Courier Prime", Font.BOLD, 12));
		panelCentro.setBackground(new Color(0, 64, 0));
		frmScrabble.getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 1, 0, 0));
		
		tablero = new JTextArea();
		tablero.setEditable(false);
		tablero.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		tablero.setForeground(new Color(255, 255, 255));
		tablero.setBackground(new Color(0, 64, 0));
		panelCentro.add(tablero);
	}

	public void setVisible(boolean b) {
		frmScrabble.setVisible(b);
	}

	
	public void mostrarMensaje(String mensaje) {
		panelNotificaciones.setText(mensaje);
	}


	public void mostrarTablero(String obtenerTablero) {
		tablero.setText(obtenerTablero);
	}

	
	public void ingresarPalabra(ActionListener accion) {
		enviarPalabra.addActionListener(accion);
	}
	
	
	public void cambiarFichas(ActionListener accion) {
		cambioFichas.addActionListener(accion);
	}
	
	
	public void pasarTurno(ActionListener accion) {
		this.pasarTurno.addActionListener(accion);
	}
	
	
	public String recibirPalabra() {
		return palabra.getText();
	}
	
	
	public String recibirCoordenadaX() {
		return coorX.getText();
	}
	
	
	public String recibirCoordenadaY() {
		return coorY.getText();
	}
	

	public String recibirCadenaString() {
		return cadenaFichas.getText();
	}
	
	public boolean isSelected() {
		return horizontal.isSelected();
	}
	
	public void mostrarEstadoJugador(IJugador jugador, int cantidadFichas) {
		this.turnoDe.setText(jugador.getNombre());
		this.puntaje.setText(jugador.getPuntaje() + "");
		this.atril.setText(jugador.getAtril().toString());
		this.cntFichas.setText(cantidadFichas + "");
	}

	public JFrame parentComponent() {
		return frmScrabble;
	}

	public void limpiar() {
		palabra.setText("");
		coorX.setText("");
		coorY.setText("");
		cadenaFichas.setText("");
	}
	
	public void cerrar(WindowAdapter accion) {
		frmScrabble.addWindowListener(accion);
	}


	public void desconectar(ActionListener accion) {
		desconectar.addActionListener(accion);
	}
	



	


	
	

}
