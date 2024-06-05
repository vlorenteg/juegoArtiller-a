package es.studium.Juego;

import java.awt.Color;
import java.awt.Graphics;

public class Circulo {
    int x, y, radio, alturaRectangulo;
    String nombre;

    public Circulo(int x, int y, int radio, String nombre, int alturaRectangulo) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.nombre = nombre;
        this.alturaRectangulo = alturaRectangulo;
    }

    public void dibujar(Graphics g) {
        // Dibujar el rectángulo
        g.setColor(Color.GRAY);
        g.fillRect(x - radio, y + radio, radio * 2, alturaRectangulo);

        // Dibujar el círculo
        g.setColor(Color.RED);
        g.fillOval(x - radio, y - radio, radio * 2, radio * 2);
        g.setColor(Color.BLACK);
        g.drawString(nombre, x - radio, y - radio - 5);
    }
}
