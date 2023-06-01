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

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
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
