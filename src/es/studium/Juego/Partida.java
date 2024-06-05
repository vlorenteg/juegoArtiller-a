package es.studium.Juego;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Random;

public class Partida extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel panelJugadores;
    private JPanel panelControles;
    private JTextField txtVelocidad;
    private JTextField txtGrado;
    private JButton btnDisparar;
    private JLabel lblJugadorTurno;
    private String[] nombresJugadores;
    private int jugadorTurno;
    private int numJugadores;
    private Circulo[] jugadores;
    private Bola bola;
    private Modelo modelo;
    Toolkit herramientas;
    Image tablero;

    public Partida(int numJugadores, String... nombres) {
        this.setNumJugadores(numJugadores);
        this.nombresJugadores = nombres;
        this.jugadorTurno = 0;

        herramientas = Toolkit.getDefaultToolkit();
        tablero = herramientas.getImage(getClass().getResource("/FONDO PARTIDA.jpg"));
        
        modelo = new Modelo();

        // Configurar la ventana principal
        setTitle("Artillería");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel izquierdo (Tablero)
        panelIzquierdo = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(tablero, 0, 0, this.getWidth(), this.getHeight(), this);
                dibujarTablero(g);
            }
        };
        panelIzquierdo.setPreferredSize(new Dimension(500, 600));
        panelIzquierdo.setBackground(Color.WHITE);
        add(panelIzquierdo, BorderLayout.CENTER);

        // Panel derecho (Controles y Jugadores)
        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(300, 600));
        add(panelDerecho, BorderLayout.EAST);

        // Panel de Jugadores
        panelJugadores = new JPanel();
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));
        panelJugadores.setBorder(BorderFactory.createTitledBorder("Jugadores"));
        panelDerecho.add(panelJugadores, BorderLayout.CENTER);

        // Panel de Controles
        panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(6, 2));
        panelControles.setBorder(BorderFactory.createTitledBorder("Controles de Disparo"));

        lblJugadorTurno = new JLabel("Turno de: " + nombresJugadores[jugadorTurno]);
        panelControles.add(lblJugadorTurno);

        panelControles.add(new JLabel("Velocidad:"));
        txtVelocidad = new JTextField();
        panelControles.add(txtVelocidad);

        panelControles.add(new JLabel("Grado de Inclinación:"));
        txtGrado = new JTextField();
        panelControles.add(txtGrado);

        btnDisparar = new JButton("Disparar");
        panelControles.add(btnDisparar);

        panelDerecho.add(panelControles, BorderLayout.SOUTH);

        // Crear los jugadores
        jugadores = new Circulo[numJugadores];
        Random rand = new Random();
        for (int i = 0; i < numJugadores; i++) {
            int alturaRectangulo = 50 + rand.nextInt(200);
            jugadores[i] = new Circulo(50 + i * 100, 500 - alturaRectangulo, 20, nombresJugadores[i], alturaRectangulo);
            JLabel lblJugador = new JLabel(nombresJugadores[i]);
            panelJugadores.add(lblJugador);
        }

        setVisible(true);
    }

    private void dibujarTablero(Graphics g) {
        // Dibujar los jugadores si existen
        if (jugadores != null) {
            for (Circulo jugador : jugadores) {
                if (jugador != null) {
                    jugador.dibujar(g);
                }
            }
        }

        // Dibujar la bola si existe
        if (bola != null) {
            bola.dibujar(g);
        }
    }

    public void setControlador(ActionListener controlador) {
        btnDisparar.addActionListener(controlador);
    }

    public JButton getBtnDisparar() {
        return btnDisparar;
    }

    public JTextField getTxtVelocidad() {
        return txtVelocidad;
    }

    public JTextField getTxtGrado() {
        return txtGrado;
    }

    public Circulo[] getJugadores() {
        return jugadores;
    }

    public int getJugadorTurno() {
        return jugadorTurno;
    }

    public void setBola(Bola bola) {
        this.bola = bola;
    }

    public Bola getBola() {
        return bola;
    }

    public void setJugadorTurno(int jugadorTurno) {
        this.jugadorTurno = jugadorTurno;
    }

    public String[] getNombresJugadores() {
        return nombresJugadores;
    }

    public void actualizarTurnoLabel() {
        lblJugadorTurno.setText("Turno de: " + nombresJugadores[jugadorTurno]);
    }

    public Modelo getModelo() {
        return modelo;
    }

	public int getNumJugadores()
	{
		return numJugadores;
	}

	public void setNumJugadores(int numJugadores)
	{
		this.numJugadores = numJugadores;
	}
}
