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

public class Premio extends SuperClaseInfo {
	private String marca;
	private double puntosNecesarios;
	private boolean disponibilidad;
	private String imagenUrl;
	private int premioDiscoteca;
	

	
	public Premio(String nombre, String descripcion, String marca, double puntosNecesarios, boolean disponibilidad,
			String imagenUrl, int premioDiscoteca) {
		super(nombre, descripcion);
		this.marca = marca;
		this.puntosNecesarios = puntosNecesarios;
		this.disponibilidad = disponibilidad;
		this.imagenUrl = imagenUrl;
		this.premioDiscoteca = premioDiscoteca;
	}
	

	public int getPremioDiscoteca() {
		return premioDiscoteca;
	}


	public void setPremioDiscoteca(int premioDiscoteca) {
		this.premioDiscoteca = premioDiscoteca;
	}


	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}
	public static void registrarPremio(String nombre, String descripcion, String marca, double puntosNecesarios, boolean disponibilidad,String imagenUrl,int premioDiscoteca)
			throws SQLException, ConexionFallidaException {
		try (Connection connection = DAO.connect()) {

			String checkQuery = "SELECT * FROM Premio WHERE nombre = ? AND imagenUrl = ?";
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setString(1, nombre);
			checkStatement.setString(2, imagenUrl);
			ResultSet resultSet = checkStatement.executeQuery();
			if (resultSet.next()) {
				throw new SQLException("El Premio ya existe");
			}

			String query = "INSERT INTO Premio (nombre, marca, descripcion, puntosNecesarios, disponible, imagenUrl, idDiscoteca) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nombre);
			statement.setString(2, marca);
			statement.setString(3, descripcion);
			statement.setDouble(4, puntosNecesarios);
			statement.setBoolean(5, disponibilidad);
			statement.setString(6, imagenUrl);
			statement.setInt(7, premioDiscoteca);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Premio registrado exitosamente!");
			}
		}
	}
	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	public static void borrarPremio(String nombre, String imagenUrl) throws SQLException, ConexionFallidaException {
	    try (Connection connection = DAO.connect()) {
	        String query = "DELETE FROM Premio WHERE nombre = ? AND imagenUrl = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, nombre);
	        statement.setString(2, imagenUrl);

	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Premio eliminado exitosamente!");
	        } else {
	            throw new SQLException("El Premio no existe");
	        }
	    }
	}

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public static List<Premio> obtenerPremios() {
		List<Premio> premios = new ArrayList<>();

		try {
			// Utilizar DAO para obtener la conexión a la base de datos
			Connection connection = DAO.connect();
			Statement statement = connection.createStatement();

			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM premio");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String marca = resultSet.getString("marca");
				String descripcion= resultSet.getString("descripcion");
				int puntosNecesarios = resultSet.getInt("puntosNecesarios");
				boolean disponibilidad = resultSet.getBoolean("disponible");
				String imagenUrl = resultSet.getString("imagenUrl");
				int premioDiscoteca = resultSet.getInt("idDiscoteca");
	
				Premio premio = new Premio(nombre,marca,descripcion,puntosNecesarios, disponibilidad, imagenUrl,premioDiscoteca); // Pasar imagenUrl al constructor
				premios.add(premio);
			}

			// Cerrar la conexión a la base de datos
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

		return premios;
	}
	
	public double getPuntosNecesarios() {
		return puntosNecesarios;
	}
	public void setPuntosNecesarios(double puntosNecesarios) {
		this.puntosNecesarios = puntosNecesarios;
	}


	@Override
	public String toString() {
		return "Premio [marca=" + marca + ", puntosNecesarios=" + puntosNecesarios + ", disponibilidad="
				+ disponibilidad + ", imagenUrl=" + imagenUrl + ", premioDiscoteca=" + premioDiscoteca + "]";
	}
	

	

}
