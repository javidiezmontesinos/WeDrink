package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.ConexionFallidaException;
import utils.DAO;

public class Logro extends SuperClaseInfo {
	private int id_logros;
	private String imagenUrl;
	private int puntosObtenidosLogros;
	private boolean logroCompletado;

	public Logro(String nombre, String descripcion, String imagenUrl, int puntosObtenidosLogros,
			boolean logroCompletado) {
		super(nombre, descripcion);
		this.imagenUrl = imagenUrl;
		this.puntosObtenidosLogros = puntosObtenidosLogros;
		this.logroCompletado = logroCompletado;
	}

	public boolean isLogroCompletado() {
		return logroCompletado;
	}

	public static List<Logro> obtenerLogros() {
		List<Logro> logros = new ArrayList<>();

		try {
			// Utilizar DAO para obtener la conexión a la base de datos
			Connection connection = DAO.connect();
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM logros");

			while (resultSet.next()) {
				
				String nombre = resultSet.getString("nombre");
				String imagenUrl = resultSet.getString("imagenlogro");
				String descripcion = resultSet.getString("descripcion");
				int puntosObtenidosLogros = resultSet.getInt("puntos");
				boolean logroCompletado = resultSet.getBoolean("logrocompletado");
				Logro logro = new Logro(nombre, imagenUrl, descripcion,puntosObtenidosLogros,logroCompletado); // Pasar imagenUrl al constructor
				logros.add(logro);
			}

			// Cerrar la conexión a la base de datos
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

		return logros;
	}

	public void setLogroCompletado(boolean logroCompletado) {
		this.logroCompletado = logroCompletado;
	}

	public int getId_logros() {
		return id_logros;
	}

	public void setId_logros(int id_logros) {
		this.id_logros = id_logros;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public int getPuntosObtenidosLogros() {
		return puntosObtenidosLogros;
	}

	public void setPuntosObtenidosLogros(int puntosObtenidosLogros) {
		this.puntosObtenidosLogros = puntosObtenidosLogros;
	}

	@Override
	public String toString() {
		return "Logros [id_logros=" + id_logros + ", imagenUrl=" + imagenUrl + ", puntosObtenidosLogros="
				+ puntosObtenidosLogros + ", logroCompletado=" + logroCompletado + "]";
	}

}
