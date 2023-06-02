package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import exceptions.ConexionFallidaException;
import utils.DAO;

public class Evento extends SuperClaseLugar {
	private Date fecha;
	private int puntosPorAsistir;
	private HashSet<Usuario> asistentes;
	private double precioEvento;
	private String imagenUrl;

	public Evento(String nombre, String descripcion, String localidad, String direcion, Date fecha,
			int puntosPorAsistir, HashSet<Usuario> asistentes, double precioEvento, String imagenUrl) {
		super(nombre, descripcion, localidad, direcion);
		this.fecha = fecha;
		this.puntosPorAsistir = puntosPorAsistir;
		this.asistentes = asistentes;
		this.precioEvento = precioEvento;
		this.imagenUrl = imagenUrl;
	}

	public Evento(String nombre, String localidad, String descripcion, String direccion, String imagenUrl, Date fecha,
			int puntosPorAsistir, double precioEvento) {
		super(nombre, descripcion, localidad, direccion);
		this.fecha = fecha;
		this.puntosPorAsistir = puntosPorAsistir;
		this.precioEvento = precioEvento;
		this.imagenUrl = imagenUrl;
	}

	public static List<Evento> obtenerEventos() {
		List<Evento> eventos = new ArrayList<>();

		try {
			// Utilizar DAO para obtener la conexión a la base de datos
			Connection connection = DAO.connect();
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM evento");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String localidad = resultSet.getString("localidad");
				String descripcion = resultSet.getString("descripcion");
				String direccion = resultSet.getString("direccion");
				Date fecha = resultSet.getDate("fecha");
				int puntosPorAsistir = resultSet.getInt("puntosPorAsistir");
				double precioEvento = resultSet.getDouble("precioEvento");
				String imagenUrl = resultSet.getString("imagen");

				Evento evento = new Evento(nombre, localidad, descripcion, direccion, imagenUrl, fecha,
						puntosPorAsistir, precioEvento); // Pasar imagenUrl al constructor
				eventos.add(evento);
			}

			// Cerrar la conexión a la base de datos
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

		return eventos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection.prepareStatement("UPDATE evento SET fecha = ? WHERE imagen = ?");
			statement.setDate(1, new java.sql.Date(fecha.getTime()));
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}
	}

	public int getPuntosPorAsistir() {
		return puntosPorAsistir;
	}

	public void setPuntosPorAsistir(int puntosPorAsistir) {
		this.puntosPorAsistir = puntosPorAsistir;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE evento SET puntosPorAsistir = ? WHERE imagen = ?");
			statement.setInt(1, puntosPorAsistir);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}
	}

	public HashSet<Usuario> getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(HashSet<Usuario> asistentes) {
		this.asistentes = asistentes;
	}

	public double getPrecioEvento() {
		return precioEvento;
	}

	public void setPrecioEvento(double precioEvento) {
		this.precioEvento = precioEvento;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE evento SET precioEvento = ? WHERE imagen = ?");
			statement.setDouble(1, precioEvento);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection.prepareStatement("UPDATE evento SET imagen = ? WHERE imagen = ?");
			statement.setString(1, imagenUrl);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Evento [fecha=" + fecha + ", puntosPorAsistir=" + puntosPorAsistir + ", asistentes=" + asistentes
				+ ", precioEvento=" + precioEvento + ", imagenUrl=" + imagenUrl + "]";
	}

}
