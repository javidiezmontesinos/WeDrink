package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.ConexionFallidaException;
import utils.DAO;

public class Producto extends SuperClaseInfo {
	private int puntosPorCompra;
	private double precioProducto;
	private String imagenUrl;

	public Producto(String nombre, String descripcion, int puntosPorCompra, double precioProducto, String imagenUrl) { // Nuevo argumento en el constructor
		super(nombre, descripcion);
		this.puntosPorCompra = puntosPorCompra;
		this.precioProducto = precioProducto;
		this.imagenUrl = imagenUrl;
	}
	public static void registrarProducto(String nombre, String descripcion, int puntosPorCompra, double precioProducto, String imagenUrl)
			throws SQLException, ConexionFallidaException {
		try (Connection connection = DAO.connect()) {

			String checkQuery = "SELECT * FROM Producto WHERE nombre = ? AND imagenUrl = ?";
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setString(1, nombre);
			checkStatement.setString(2, imagenUrl);
			ResultSet resultSet = checkStatement.executeQuery();
			if (resultSet.next()) {
				throw new SQLException("El evento ya existe");
			}

			String query = "INSERT INTO Producto (nombre, descripcion, puntosPorCompra, precioProducto,imagenUrl) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nombre);
			statement.setString(2, descripcion);
			statement.setInt(3, puntosPorCompra);
			statement.setDouble(4, precioProducto);
			statement.setString(5, imagenUrl);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Producto en promocion registrado exitosamente!");
			}
		}
	}
	public static void borrarProducto(String nombre, String imagenUrl) throws SQLException, ConexionFallidaException {
	    try (Connection connection = DAO.connect()) {
	        String query = "DELETE FROM Producto WHERE nombre = ? AND imagenUrl = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, nombre);
	        statement.setString(2, imagenUrl);

	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Producto eliminado exitosamente!");
	        } else {
	            throw new SQLException("El Producto no existe");
	        }
	    }
	}
	public int getPuntosPorCompra() {
		return puntosPorCompra;
	}

	public void setPuntosPorCompra(int puntosPorCompra) {
		this.puntosPorCompra = puntosPorCompra;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection.prepareStatement("UPDATE producto SET puntosPorCompra = ? WHERE imagenUrl = ?");
			statement.setInt(1, puntosPorCompra);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}
	
	}
	

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
		try {
			Connection connection = DAO.connect();
			PreparedStatement statement = connection.prepareStatement("UPDATE producto SET precioProducto = ? WHERE imagenUrl = ?");
			statement.setDouble(1, precioProducto);
			statement.setString(2, this.imagenUrl);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}
	
	
	}

	public String getImagenUrl() { // Nuevo getter
		return imagenUrl;
	}

	public static List<Producto> obtenerProductos() {
		List<Producto> productos = new ArrayList<>();

		try {
			
			Connection connection = DAO.connect();
			Statement statement = connection.createStatement();

			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Producto");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String descripcion = resultSet.getString("descripcion");
				int puntosPorCompra = resultSet.getInt("puntosPorCompra");
				double precioProducto = resultSet.getDouble("precioProducto");
				String imagenUrl = resultSet.getString("imagenUrl");

				Producto producto = new Producto(nombre, descripcion, puntosPorCompra, precioProducto, imagenUrl); 
				productos.add(producto);
			}

			
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

		return productos;
	}

	@Override
	public String toString() {
		return "Producto [puntosPorCompra=" + puntosPorCompra + ", precioProducto=" + precioProducto + ", imagenUrl=" + imagenUrl + "]"; 
	}

	
}
