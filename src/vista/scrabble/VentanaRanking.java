package vista.scrabble;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import modelo.scrabble.Jugador;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class VentanaRanking implements Ventana{

	private JFrame frmScrabble;
	private JTextArea ranking;

	public VentanaRanking() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		frmScrabble = new JFrame();
		frmScrabble.setTitle("Scrabble");
		frmScrabble.setBounds(100, 100, 510, 380);
		frmScrabble.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setPreferredSize(new Dimension(10, 30));
		frmScrabble.getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("TOP 5 MEJORES JUGADORES");
		titulo.setFont(new Font("Segoe UI Variable Static Text", Font.BOLD, 13));
		panelNorte.add(titulo);
		
		ranking = new JTextArea();
		frmScrabble.getContentPane().add(ranking, BorderLayout.CENTER);
	}

	public void setVisible(boolean b) {
		frmScrabble.setVisible(b);
		
	}

	public void mostrarRanking(ArrayList<Jugador> top5jugadores) {
		int i = 1;
		String dato = "";
		mostrarMensaje("");
		mostrarMensaje("Jugador		Puntaje\n");
		for(Jugador j: top5jugadores) {
			dato += (i + ". ") + j.getNombre() + ". ......... " + j.getPuntaje() + ".\n";
		}
		mostrarMensaje(dato);
	}

	private void mostrarMensaje(String string) {
		ranking.setText(string);
	}

}
