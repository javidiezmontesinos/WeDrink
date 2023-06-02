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
		this.imagenUrl = imagenUrl; // Inicializar el nuevo atributo
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
	public int getPuntosPorCompra() {
		return puntosPorCompra;
	}

	public void setPuntosPorCompra(int puntosPorCompra) {
		this.puntosPorCompra = puntosPorCompra;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public String getImagenUrl() { // Nuevo getter
		return imagenUrl;
	}

	public static List<Producto> obtenerProductos() {
		List<Producto> productos = new ArrayList<>();

		try {
			// Utilizar DAO para obtener la conexión a la base de datos
			Connection connection = DAO.connect();
			Statement statement = connection.createStatement();

			// Obtener los productos de la tabla "Productos"
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Producto");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String descripcion = resultSet.getString("descripcion");
				int puntosPorCompra = resultSet.getInt("puntosPorCompra");
				double precioProducto = resultSet.getDouble("precioProducto");
				String imagenUrl = resultSet.getString("imagenUrl"); // Obtener la imagenUrl del producto

				Producto producto = new Producto(nombre, descripcion, puntosPorCompra, precioProducto, imagenUrl); // Pasar imagenUrl al constructor
				productos.add(producto);
			}

			// Cerrar la conexión a la base de datos
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
		return "Producto [puntosPorCompra=" + puntosPorCompra + ", precioProducto=" + precioProducto + ", imagenUrl=" + imagenUrl + "]"; // Imprimir también imagenUrl
	}

	//metodos
	
}
