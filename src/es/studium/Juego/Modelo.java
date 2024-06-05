package es.studium.Juego;

import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/juego";
	String login = "user"; // Usuario MySQL
	String password = "user"; // Clave correspondiente
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	Modelo()
	{
		connection = this.conectar();
	}
	
	public Connection conectar()
	{
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD
			return(DriverManager.getConnection(url, login, password));
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		}
		return null;
	}
	
	
	public void rellenarRanking(TextArea txaListado)
	{
		String sentencia = "select nombreJugador, puntosJugador from jugadores where puntosJugador order by puntosJugador DESC;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			while (resultado.next())
			{
				txaListado.append(resultado.getString("nombreJugador") + " - ");
				txaListado.append(resultado.getString("puntosJugador") + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-" + sqle.getMessage());
		}		
	}
	
	public void actualizarRanking(String nombre, int puntos)
	{
		String sentenciaSelect = "SELECT puntosJugador FROM jugadores WHERE nombreJugador = '" + nombre + "';";
		String sentenciaUpdate;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentenciaSelect);
			
			if (resultado.next()) {
				int puntosActuales = resultado.getInt("puntosJugador");
				int nuevosPuntos = puntosActuales + puntos;
				sentenciaUpdate = "UPDATE jugadores SET puntosJugador = " + nuevosPuntos + " WHERE nombreJugador = '" + nombre + "';";
			} else {
				sentenciaUpdate = "INSERT INTO jugadores (nombreJugador, puntosJugador) VALUES ('" + nombre + "', " + puntos + ");";
			}
			
			statement.executeUpdate(sentenciaUpdate);
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-" + sqle.getMessage());
		}
	}
}
