package es.studium.Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;



public class Controlador implements WindowListener, ActionListener
{
	Modelo modelo;
	MenuPrincipal menuPrincipal;
	TopRanking ranking = new TopRanking();
	Partida partida;
	Jugadores jugadores = new Jugadores();
	boolean haElegidoJugadores = false;
	int numJugadores;
	String[] nombresJugadores;
	
	public Controlador(Modelo m, MenuPrincipal mp, Partida p, TopRanking r, Jugadores ej)
	{
		this.modelo = m;
		this.menuPrincipal = mp;
		this.partida = p;
		this.ranking = r;
		this.jugadores = ej;
		
		this.menuPrincipal.addWindowListener(this);
		this.menuPrincipal.btnTopRanking.addActionListener(this); 
		this.menuPrincipal.btnAyuda.addActionListener(this); 
		this.menuPrincipal.btnPartidaNueva.addActionListener(this); 
		this.menuPrincipal.btnSalir.addActionListener(this);
		this.ranking.addWindowListener(this);
		this.ranking.btnVolver.addActionListener(this);
		this.jugadores.btnContinuar.addActionListener(this);
		this.jugadores.btnComenzarPartida.addActionListener(this);

	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(ranking.isActive())
		{
			ranking.OcultarTopRanking();
			menuPrincipal.setVisible(true);
		}
		else if(jugadores.pedirNumeroJugadores.isActive())
		{
			jugadores.pedirNumeroJugadores.removeWindowListener(this);
			jugadores.btnContinuar.removeActionListener(this);
			jugadores.OcultarDialogNumeroJugadores();
			jugadores.pedirNumeroJugadores.dispose();
			menuPrincipal.setVisible(true);
		}
		else if(jugadores.pedirNombresJugadores.isActive()) 
		{
			jugadores.pedirNombresJugadores.removeWindowListener(this);
			jugadores.btnComenzarPartida.removeActionListener(this);
			jugadores.choNumeroJugadores.select(0);
			jugadores.removeAll();
			jugadores.OcultarDialogNombresJugadores();
			jugadores.pedirNombresJugadores.dispose();
			menuPrincipal.setVisible(true);
		}
		else if((partida!=null)&&(partida.isActive())) 
		{
			partida.removeWindowListener(this);
			partida.setVisible(false);
			partida.dispose();
			jugadores.OcultarDialogNombresJugadores();
			menuPrincipal.setVisible(true);
		}
		else
		{
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object botonPulsado = e.getSource();
		if(botonPulsado.equals(menuPrincipal.btnSalir))
		{
			System.exit(0);
		}
		else if(botonPulsado.equals(menuPrincipal.btnTopRanking)) 
		{
			ranking.MostrarTopRanking();
			menuPrincipal.setVisible(false);
		}
		else if(botonPulsado.equals(ranking.btnVolver)) 
		{
			ranking.OcultarTopRanking();
			menuPrincipal.setVisible(true);
		}
		else if(botonPulsado.equals(menuPrincipal.btnAyuda))
		{
			try
			{
				Runtime.getRuntime().exec("hh.exe AYUDA.chm");
			}
			catch (IOException er)
			{
				er.printStackTrace();
			}

		}
		else if(botonPulsado.equals(menuPrincipal.btnPartidaNueva))
		{
			jugadores.MostrarDialogNumeroJugadores();
			menuPrincipal.setVisible(false);
		}
		else if(botonPulsado.equals(jugadores.btnContinuar)) 
		{
			if(!jugadores.choNumeroJugadores.getSelectedItem().equals("Elegir número de jugadores..."))
			{
				jugadores.PrepararDialogNombresJugadores(Integer.parseInt(jugadores.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(jugadores.btnComenzarPartida)) 
		{
			numJugadores = Integer.parseInt(jugadores.choNumeroJugadores.getSelectedItem());

			if((numJugadores==4)&&(!jugadores.txfNombre1.getText().equals(""))
					&&(!jugadores.txfNombre2.getText().equals(""))
					&&(!jugadores.txfNombre3.getText().equals(""))
					&&(!jugadores.txfNombre4.getText().equals("")))
			{
				partida = new Partida(4, jugadores.txfNombre1.getText(),jugadores.txfNombre2.getText(),jugadores.txfNombre3.getText(),jugadores.txfNombre4.getText(), null);
				partida.addWindowListener(this);
				partida.setVisible(true);
				jugadores.setVisible(false);
			}
			else if((numJugadores==3)&&(!jugadores.txfNombre1.getText().equals(""))
					&&(!jugadores.txfNombre2.getText().equals(""))
					&&(!jugadores.txfNombre3.getText().equals("")))
			{
				partida = new Partida(3,jugadores.txfNombre1.getText(),jugadores.txfNombre2.getText(),jugadores.txfNombre3.getText(),"", null);
				partida.addWindowListener(this);
				partida.setVisible(true);
				jugadores.setVisible(false);
			}
			else if((numJugadores==2)&&(!jugadores.txfNombre1.getText().equals(""))
					&&(!jugadores.txfNombre2.getText().equals("")))
			{
				partida = new Partida(2,jugadores.txfNombre1.getText(),jugadores.txfNombre2.getText(),"", null, null);
				partida.addWindowListener(this);
				partida.setVisible(true);
				jugadores.setVisible(false);
			}
			
			else
			{
				jugadores.txfNombre1.requestFocus();
			}
		
		partida.setControlador(this); // Aquí se establece el controlador
        partida.addWindowListener(this);
        partida.setVisible(true);
        jugadores.setVisible(false);
	}
		else if (botonPulsado.equals(partida.getBtnDisparar())) {
            disparar();
        }
    }

    public void disparar() {
        String velocidadStr = partida.getTxtVelocidad().getText();
        String gradoStr = partida.getTxtGrado().getText();
        try {
            int velocidad = Integer.parseInt(velocidadStr);
            int grado = Integer.parseInt(gradoStr);

            if (partida.getJugadores()[partida.getJugadorTurno()] == null) {cambiarTurno();
                return;
            }

            Circulo jugadorActual = partida.getJugadores()[partida.getJugadorTurno()];
            int bolaX = jugadorActual.x;
            int bolaY = jugadorActual.y - jugadorActual.radio - 10;
            partida.setBola(new Bola(bolaX, bolaY, 10, velocidad, grado));

            Timer timer = new Timer(30, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (partida.getBola().mover()) {
                        verificarColisiones();
                        partida.repaint();
                    } else {
                        ((Timer) e.getSource()).stop();
                        partida.setBola(null);
                        partida.repaint();
                    }
                }
            });
            timer.start();

            cambiarTurno();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(partida, "Velocidad o grado de inclinación inválido. Introduzca números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cambiarTurno() {
        do {
            partida.setJugadorTurno((partida.getJugadorTurno() + 1) % partida.getJugadores().length);
        } while (partida.getJugadores()[partida.getJugadorTurno()] == null);
        partida.actualizarTurnoLabel();
    }
    
    public void verificarColisiones() {
        Circulo[] jugadores = partida.getJugadores();
        Bola bola = partida.getBola();
        int numJugadores = jugadores.length;
        String[] nombresJugadores = partida.getNombresJugadores();
        Modelo modelo = partida.getModelo();

        for (int i = 0; i < numJugadores; i++) {
            if (i != partida.getJugadorTurno() && jugadores[i] != null) {
                if (bola.colisiona(jugadores[i])) {
                    jugadores[i] = null;
                    JOptionPane.showMessageDialog(partida, "¡" + nombresJugadores[i] + " ha sido eliminado!", "Jugador Eliminado", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }

        int jugadoresRestantes = 0;
        String nombreUltimoJugador = "";
        for (int i = 0; i < numJugadores; i++) {
            if (jugadores[i] != null) {
                jugadoresRestantes++;
                nombreUltimoJugador = nombresJugadores[i];
            }
        }

        if (jugadoresRestantes == 1) {
            JOptionPane.showMessageDialog(partida, "¡" + nombreUltimoJugador + " es el ganador! 10 Puntos", "Fin de la partida", JOptionPane.INFORMATION_MESSAGE);
            modelo.actualizarRanking(nombreUltimoJugador, 10);
            System.exit(0);
        }
    }
		
	

}
