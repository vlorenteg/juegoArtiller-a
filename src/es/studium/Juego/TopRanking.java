package es.studium.Juego;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;


public class TopRanking extends Frame
{
	private static final long serialVersionUID = 1L;

    private Label lblRanking = new Label("TOP RANKING:");

    private TextArea txaListado = new TextArea(13, 30);

    Button btnVolver = new Button("Volver");

    private Modelo c = new Modelo();

    TopRanking()
    {
        setTitle("Rankings");
        setSize(300, 300);
        setLayout(new FlowLayout());
        setBackground(Color.CYAN);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(false);
        txaListado.append("Jugador  -  Puntos\n\n");
        c.rellenarRanking(txaListado);
        add(btnVolver, "South");

        add(lblRanking);
        add(txaListado);
 

        txaListado.setEditable(false);


    }
    
	public void MostrarTopRanking()
	{
		this.setVisible(true);
	}
	
	public void OcultarTopRanking()
	{
		this.setVisible(false);
	}
}
