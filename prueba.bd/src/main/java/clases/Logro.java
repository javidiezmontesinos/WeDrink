package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public Logro(String nombre, String imagenUrl, String descripcion, int puntosObtenidosLogros,
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

			Connection connection = DAO.connect();
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM logros");

			while (resultSet.next()) {

				String nombre = resultSet.getString("nombre");
				String imagenUrl = resultSet.getString("imagenlogro");
				String descripcion = resultSet.getString("descripcion");
				int puntosObtenidosLogros = resultSet.getInt("puntos");
				boolean logroCompletado = resultSet.getBoolean("logrocompletado");
				Logro logro = new Logro(nombre, imagenUrl, descripcion, puntosObtenidosLogros, logroCompletado);

				logros.add(logro);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

		return logros;
	}

	public static void registrarLogro(String nombre, String imagenUrl, String descripcion, double puntosObtenidosLogros,
			boolean logroCompletado) throws SQLException, ConexionFallidaException {
		try (Connection connection = DAO.connect()) {

			String checkQuery = "SELECT * FROM Logros WHERE nombre = ? AND imagenlogro = ?";
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setString(1, nombre);
			checkStatement.setString(2, imagenUrl);
			ResultSet resultSet = checkStatement.executeQuery();
			if (resultSet.next()) {
				throw new SQLException("El Logro ya existe");
			}

			String query = "INSERT INTO Logros (nombre, imagenlogro, descripcion, puntos, logrocompletado) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nombre);
			statement.setString(2, imagenUrl);
			statement.setString(3, descripcion);
			statement.setDouble(4, puntosObtenidosLogros);
			statement.setBoolean(5, logroCompletado);
			statement.setString(6, imagenUrl);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Logro registrado exitosamente!");
			}
		}
	}

	public static void borrarLogro(String nombre, String imagenUrl) throws SQLException, ConexionFallidaException {
		try (Connection connection = DAO.connect()) {
			String query = "DELETE FROM Logros WHERE nombre = ? AND imagenlogro = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nombre);
			statement.setString(2, imagenUrl);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Logro eliminado exitosamente!");
			} else {
				throw new SQLException("El logro no existe");
			}
		}
	}

	public void setLogroCompletado(boolean logroCompletado) {
		this.logroCompletado = logroCompletado;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE logros SET logrocompletado = ? WHERE imagenlogro = ?");
			statement.setBoolean(1, logroCompletado);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

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
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE logros SET imagenlogro = ? WHERE imagenlogro = ?");
			statement.setString(1, imagenUrl);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

	}

	public int getPuntosObtenidosLogros() {
		return puntosObtenidosLogros;
	}

	public void setPuntosObtenidosLogros(int puntosObtenidosLogros) {
		this.puntosObtenidosLogros = puntosObtenidosLogros;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE logros SET puntos = ? WHERE imagenlogro = ?");
			statement.setDouble(1, puntosObtenidosLogros);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Logros [id_logros=" + id_logros + ", imagenUrl=" + imagenUrl + ", puntosObtenidosLogros="
				+ puntosObtenidosLogros + ", logroCompletado=" + logroCompletado + "]";
	}

}
