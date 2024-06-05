package es.studium.Juego;

import java.awt.Color;
import java.awt.Graphics;

public class Bola {
    int x, y, radio;
    int velocidadX, velocidadY;

    public Bola(int x, int y, int radio, int velocidad, int grado) {
        this.x = x;
        this.y = y;
        this.radio = radio;

        // Convertir el ángulo en radianes para calcular la velocidad en x e y
        double radianes = Math.toRadians(grado);
        this.velocidadX = (int) (velocidad * Math.cos(radianes));
        this.velocidadY = (int) (-velocidad * Math.sin(radianes)); // Velocidad negativa para moverse hacia arriba
    }

    public boolean mover() {
        // Actualizar la posición
        x += velocidadX;
        y += velocidadY;

        // Aplicar gravedad
        velocidadY += 1;

        // Condiciones para detener la bola (cuando sale de la pantalla)
        return x > 0 && x < 800 && y < 600;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x - radio, y - radio, radio * 2, radio * 2);
    }

    public boolean colisiona(Circulo jugador) {
        // Calculamos la distancia entre el centro de la bola y el centro del jugador
        double distanciaCentros = Math.sqrt(Math.pow((x - jugador.x), 2) + Math.pow((y - jugador.y), 2));
        
        // Si la distancia entre los centros de la bola y el jugador es menor o igual a la suma de los radios, hay colisión
        return distanciaCentros <= (radio + jugador.radio);
    }
}
