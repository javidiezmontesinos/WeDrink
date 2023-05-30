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

	public int getPuntosAcumuladosDiscoteca(int idCliente) {
        String sql = "SELECT puntosAcumulados FROM ClienteDiscoteca WHERE id_cliente = ?";

        try (Connection conn = DAO.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                puntosAcumuladosDiscoteca = rs.getInt("puntosAcumulados");
            }
        } catch (SQLException | ConexionFallidaException e) {
            System.out.println(e.getMessage());
        }

        return puntosAcumuladosDiscoteca;
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

}
