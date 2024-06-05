package es.studium.Juego;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class Jugadores extends Frame
{
	private static final long serialVersionUID = 1L;

	Dialog pedirNumeroJugadores = new Dialog(this, "Artillería", true);
	Dialog pedirNombresJugadores = new Dialog(this, "Artillería", true);
	int numeroJugadores = 0; 
	String[] nombresJugadores = null; 
	Choice choNumeroJugadores = new Choice();
	Button btnContinuar = new Button("Continuar...");
	
	TextField txfNombre1 = new TextField(15);
	Label lblEtiqueta1 = new Label("Jugador 1:");
	TextField txfNombre2 = new TextField(15);
	Label lblEtiqueta2 = new Label("Jugador 2:");
	TextField txfNombre3 = new TextField(15);
	Label lblEtiqueta3 = new Label("Jugador 3:");
	TextField txfNombre4 = new TextField(15);
	Label lblEtiqueta4 = new Label("Jugador 4:");

	Button btnComenzarPartida = new Button("Comenzar Partida");
	
	public Jugadores()
	{
		pedirNumeroJugadores.setBackground(Color.CYAN);
		pedirNumeroJugadores.setLayout(new FlowLayout());
		pedirNumeroJugadores.setSize(240,100);
		pedirNumeroJugadores.setLocationRelativeTo(null); 
		pedirNumeroJugadores.setResizable(false);
		

		choNumeroJugadores.add("Elegir número de jugadores...");
		choNumeroJugadores.add("2");
		choNumeroJugadores.add("3");
		choNumeroJugadores.add("4");
		pedirNumeroJugadores.add(choNumeroJugadores);
		pedirNumeroJugadores.add(btnContinuar);
	}
	
	public void MostrarDialogNumeroJugadores()
	{
		pedirNumeroJugadores.setVisible(true);
	}

	public void OcultarDialogNumeroJugadores()
	{
		pedirNumeroJugadores.setVisible(false);
	}
	public void MostrarDialogNombresJugadores()
	{
		pedirNombresJugadores.setVisible(true);
	}

	public void OcultarDialogNombresJugadores()
	{
		pedirNombresJugadores.setVisible(false);
	}

	public void PrepararDialogNombresJugadores(int numero)
	{
		pedirNombresJugadores.setBackground(Color.CYAN); 
		pedirNombresJugadores.setLayout(new FlowLayout()); 
		pedirNombresJugadores.setSize(240,200);
		pedirNombresJugadores.setLocationRelativeTo(null);
		pedirNombresJugadores.setResizable(false);

		pedirNombresJugadores.removeAll();
		
		// Jugador 1, siempre existe
		pedirNombresJugadores.add(lblEtiqueta1);
		txfNombre1.selectAll(); 
		txfNombre1.setText("");
		pedirNombresJugadores.add(txfNombre1);
		// Jugador 2, siempre existe
		pedirNombresJugadores.add(lblEtiqueta2);
		txfNombre2.selectAll(); 
		txfNombre2.setText("");
		pedirNombresJugadores.add(txfNombre2);

		if(numero == 3) // Son 3 jugadores
		{
			pedirNombresJugadores.add(lblEtiqueta3);
			txfNombre3.selectAll(); // Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.add(txfNombre3);
		}
		if(numero == 4) // Son 4 jugadores
		{
			pedirNombresJugadores.add(lblEtiqueta3);
			txfNombre3.selectAll(); // Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.add(txfNombre3);
			pedirNombresJugadores.add(lblEtiqueta4);
			txfNombre4.selectAll(); 
			txfNombre4.setText("");
			pedirNombresJugadores.add(txfNombre4);
		}

		
			pedirNombresJugadores.add(btnComenzarPartida);
		    OcultarDialogNumeroJugadores(); 
		    MostrarDialogNombresJugadores();
}}
