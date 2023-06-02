package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ConexionFallidaException;
import utils.DAO;

public class ClienteDiscoteca {
	private int puntosAcumuladosDiscoteca;
	private Discoteca discoteca;
	private Usuario usuarioCliente;

	public ClienteDiscoteca(int puntosAcumuladosDiscoteca, Discoteca discoteca, Usuario usuarioCliente) {
		super();
		this.puntosAcumuladosDiscoteca = puntosAcumuladosDiscoteca;
		this.discoteca = discoteca;
		this.usuarioCliente = usuarioCliente;
	}

	public int getPuntosAcumuladosDiscoteca() {
		int puntosAcumulados = 0;

		try (Connection connection = DAO.connect()) {
			String query = "SELECT puntosAcumuladosDiscoteca FROM ClienteDiscoteca "
					+ "INNER JOIN Usuario ON ClienteDiscoteca.id_cliente = Usuario.id "
					+ "INNER JOIN Discoteca ON ClienteDiscoteca.discoteca_id = Discoteca.id "
					+ "WHERE Usuario.correo = ? AND Discoteca.cif = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, usuarioCliente.getCorreo());
			statement.setString(2, discoteca.getCif());
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				puntosAcumulados = resultSet.getInt("puntosAcumuladosDiscoteca");
			}
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

		return puntosAcumulados;
	}

	public void setPuntosAcumuladosDiscoteca(int puntosAcumuladosDiscoteca) {
		this.puntosAcumuladosDiscoteca = puntosAcumuladosDiscoteca;
	}

	public Discoteca getDiscoteca() {
		return discoteca;
	}

	public void setDiscoteca(Discoteca discoteca) {
		this.discoteca = discoteca;
	}

	public Usuario getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(Usuario usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	@Override
	public String toString() {
		return "ClienteDiscoteca [puntosAcumuladosDiscoteca=" + puntosAcumuladosDiscoteca + ", discoteca=" + discoteca
				+ ", usuarioCliente=" + usuarioCliente + "]";
	}

	public int PuntosAcumuladosDiscoteca() {
		int puntosAcumulados = 0;

		try (Connection connection = DAO.connect()) {
			String query = "SELECT puntos FROM PuntosEnDiscoteca "
					+ "INNER JOIN Usuario ON PuntosEnDiscoteca.usuario_id = Usuario.id "
					+ "INNER JOIN Discoteca ON PuntosEnDiscoteca.discoteca_id = Discoteca.id "
					+ "WHERE Usuario.correo = ? AND Discoteca.cif = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, usuarioCliente.getCorreo());
			statement.setString(2, discoteca.getCif());
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				puntosAcumulados = resultSet.getInt("puntos");
			}
		} catch (SQLException | ConexionFallidaException e) {
			e.printStackTrace();
		}

		return puntosAcumulados;
	}

}
