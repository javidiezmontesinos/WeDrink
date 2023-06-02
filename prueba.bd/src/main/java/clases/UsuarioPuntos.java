package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import exceptions.ConexionFallidaException;
import utils.DAO;

public class UsuarioPuntos {
	private Usuario usuarioPnts;
	private int puntosTotales;

	public UsuarioPuntos(Usuario usuarioPnts, int puntosTotales) {
		super();
		this.usuarioPnts = usuarioPnts;
		this.puntosTotales = puntosTotales;
	}



	public static List<UsuarioPuntos> obtenerPuntosTotales() {
	    List<UsuarioPuntos> puntos = new ArrayList<>();
	    try {
	        Connection connection = DAO.connect();
	        Statement statement = connection.createStatement();

	        String sql = "SELECT U.*, P.puntos " +
	                     "FROM usuario U " +
	                     "INNER JOIN usuariopuntos P ON U.id = P.usuario_id " +
	                     "ORDER BY P.puntos DESC";

	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	            String nick = resultSet.getString("nick");
	            String qrCode = resultSet.getString("qrUsuario");
	            String apellidos = resultSet.getString("apellidos");
	            String correo = resultSet.getString("correo");
	            String contraseña = resultSet.getString("contraseña");
	            int puntost = resultSet.getInt("puntos");
	            String nombre = resultSet.getString("nombre");
	            String descripcion = null;
	            String localidad = resultSet.getString("localidad");
	            String direccion = resultSet.getString("direccion");

	            HashMap<String, ClienteDiscoteca> puntosEnDiscoteca = new HashMap<>(); 
	            HashMap<String, UsuarioPuntos> puntosTotales = new HashMap<>();

	            Usuario usuario = new Usuario(nombre, descripcion, localidad, direccion, nick, qrCode, contraseña, apellidos, correo, puntosEnDiscoteca, puntosTotales);
	            UsuarioPuntos usuarioPuntos = new UsuarioPuntos(usuario, puntost);
	            puntos.add(usuarioPuntos);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException | ConexionFallidaException e) {
	        e.printStackTrace();
	    }

	    return puntos;
	}


	public Usuario getUsuarioPnts() {
		return usuarioPnts;
	}

	public void setUsuarioPnts(Usuario usuarioPnts) {
		this.usuarioPnts = usuarioPnts;
	}

	public int getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(int puntosTotales) {
	    
	}
	@Override
	public String toString() {
		return "UsuarioPuntos [usuarioPnts=" + usuarioPnts + ", puntosTotales=" + puntosTotales + "]";
	}

}
