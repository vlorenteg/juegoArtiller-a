package es.studium.Juego;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class MenuPrincipal extends Frame
	{
	
	private static final long serialVersionUID = 1L;
	
	Label lblMenuPrincipal = new Label("MENÚ PRINCIPAL", 1); 
	Panel pnlBoton = new Panel(); 
	Panel pnlBotonSalir = new Panel(); 
	Button btnPartidaNueva = new Button("Partida Nueva");
	Button btnTopRanking = new Button("Top Ranking");
	Button btnAyuda = new Button("Ayuda");
	Button btnSalir = new Button("Salir");
	public MenuPrincipal()
	{
		setTitle("Artillería"); 
		setBackground(Color.CYAN); 
		setLayout(new BorderLayout());
		pnlBoton.setLayout(new GridLayout(4,1)); 
		pnlBoton.add(lblMenuPrincipal); 
		pnlBoton.add(btnPartidaNueva); 
		pnlBoton.add(btnTopRanking); 
		pnlBoton.add(btnAyuda); 
		add(pnlBoton, "Center"); 
		pnlBotonSalir.add(btnSalir); 
		add(pnlBotonSalir, "South");
		setSize(400,200); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		MostrarPrincipal(); 
	}
	private void MostrarPrincipal()
	{
		this.setVisible(true);
	}
	public void OcultarPrincipal()
	{
		this.setVisible(false);
	}
	
}
