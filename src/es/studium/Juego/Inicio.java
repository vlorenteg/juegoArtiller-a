package es.studium.Juego;

public class Inicio
{
	public static void main(String[] args)
	{
		Modelo modelo = new Modelo();
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		Partida partida = null;
		TopRanking ranking = new TopRanking();
		Jugadores jugadores = new Jugadores();
		new Controlador(modelo, menuPrincipal, partida, ranking, jugadores);
	}
}
