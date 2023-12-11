package vista.scrabble;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import modelo.scrabble.Partida;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VentanaPartidas implements Ventana{

	private JFrame frmPartidasGuardadas;
	private JList listaPartidas;
	private DefaultListModel<String> listaModelo;
	private JButton elegirPartida;

	
	public VentanaPartidas() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		frmPartidasGuardadas = new JFrame();
		frmPartidasGuardadas.setTitle("Partidas guardadas");
		frmPartidasGuardadas.setBounds(100, 100, 524, 410);
		frmPartidasGuardadas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorte = new JPanel();
		frmPartidasGuardadas.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelEste = new JPanel();
		frmPartidasGuardadas.getContentPane().add(panelEste, BorderLayout.EAST);
		
		JPanel panelOeste = new JPanel();
		frmPartidasGuardadas.getContentPane().add(panelOeste, BorderLayout.WEST);
		
		JPanel panelSur = new JPanel();
		panelSur.setPreferredSize(new Dimension(10, 50));
		frmPartidasGuardadas.getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JLabel leyendaSur = new JLabel("Seleccione una partida y aprete \"Elegir partida\"");
		panelSur.add(leyendaSur);
		
		elegirPartida = new JButton("Elegir partida");
		panelSur.add(elegirPartida);
		
		JScrollPane scrollPane = new JScrollPane();
		frmPartidasGuardadas.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		listaPartidas = new JList();
		listaModelo = new DefaultListModel<>();
		listaPartidas.setModel(listaModelo);
		listaPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listaPartidas);
	}

	public void setVisible(boolean b) {
		frmPartidasGuardadas.setVisible(b);
	}
	
	public void elegirPartida(ActionListener accion) {
		elegirPartida.addActionListener(accion);
	}
	
    public void mostrarPartidasGuardadas(ArrayList<Object> listaPartidas) {
    	listaModelo.clear();
		for(Object o: listaPartidas) {
			Partida p = (Partida)o;
			listaModelo.addElement(p.toString());
		}
    }

	public int getSelectedIndex() {
		return listaPartidas.getSelectedIndex();
	}

}
