package clases;

import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioNoExisteException;
import utils.DAO;

public class Usuario extends SuperClaseLugar {
	private String nick;
	private BufferedImage qrCode;
	private String apellidos;
	private String correo;
	private HashMap<String, ClienteDiscoteca> puntosEnDiscoteca;
	private HashMap<String, ClienteDiscoteca> puntosTotales;

	public Usuario(String nombre, String descripcion, String localidad, String direccion, String nick,
			BufferedImage qrCode, String apellidos, String correo, HashMap<String, ClienteDiscoteca> puntosEnDiscoteca,
			HashMap<String, ClienteDiscoteca> puntosTotales) {
		super(nombre, descripcion, localidad, direccion);
		this.nick = nick;
		this.qrCode = qrCode;
		this.apellidos = apellidos;
		this.correo = correo;
		this.puntosEnDiscoteca = puntosEnDiscoteca;
		this.puntosTotales = puntosTotales;
	}

	public Usuario(String correo, String contraseña)
			throws SQLException, UsuarioNoExisteException, ContraseñaInvalidaException {
		super(nombre, descripcion, localidad, direccion);
		HashMap<String, Object> restricciones = new HashMap<String, Object>();
		restricciones.put("correo", correo);
		LinkedHashSet<String> columnasSelect = new LinkedHashSet<String>();
		columnasSelect.add("nick");
		columnasSelect.add("nombre");
		columnasSelect.add("correo");
		columnasSelect.add("contraseña");
		columnasSelect.add("localidad");
		columnasSelect.add("direccion");
		columnasSelect.add("qrUsuario");
		columnasSelect.add("apellidos");
		ArrayList<Object> ret = new ArrayList<Object>();
		ret = DAO.consultar("cliente", restricciones, columnasSelect);
		if (ret.isEmpty()) {
			throw new UsuarioNoExisteException("SIN DATOS");
		} else {
			String contraseñaAlmacenada = (String) ret.get(3);
			if (contraseñaAlmacenada.equals(contraseña)) {
				this.nick = (String) (ret.get(0));
				this.nombre = (String) (ret.get(1));
				this.correo = (String) (ret.get(2));
				contraseña = (String) (ret.get(3));
				this.localidad = (String) (ret.get(4));
				this.direccion = (String) (ret.get(5));
				this.qrCode = (BufferedImage) (ret.get(6));
				this.apellidos = (String) (ret.get(7));
			} else {
				throw new ContraseñaInvalidaException("CONTRASEÑA INVALIDA");
			}
		}

	}
	

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) throws SQLException {
		HashMap<String, Object> datosMod = new HashMap<String, Object>();
		datosMod.put("nick", nick);
		HashMap<String, Object> restriccion = new HashMap<String, Object>();
		restriccion.put("correo", this.correo);
		DAO.update("usuario", datosMod, restriccion);
		this.nick = nick;
	
	}

	public BufferedImage getQrCode() {
		return qrCode;
	}

	public void setQrCode(BufferedImage qrCode) throws SQLException {
		HashMap<String, Object> datosMod = new HashMap<String, Object>();
		datosMod.put("qrUsuario", qrCode);
		HashMap<String, Object> restriccion = new HashMap<String, Object>();
		restriccion.put("correo", this.correo);
		DAO.update("usuario", datosMod, restriccion);
		this.qrCode = qrCode;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) throws SQLException {
		HashMap<String, Object> datosMod = new HashMap<String, Object>();
		datosMod.put("apellidos", apellidos);
		HashMap<String, Object> restriccion = new HashMap<String, Object>();
		restriccion.put("correo", this.correo);
		DAO.update("usuario", datosMod, restriccion);
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) throws SQLException {
		HashMap<String, Object> datosMod = new HashMap<String, Object>();
		datosMod.put("correo", correo);
		HashMap<String, Object> restriccion = new HashMap<String, Object>();
		restriccion.put("correo", this.correo);
		DAO.update("usuario", datosMod, restriccion);
		this.correo = correo;
	}

	public HashMap<String, ClienteDiscoteca> getPuntosEnDiscoteca() {
		return puntosEnDiscoteca;
	}

	public void setPuntosEnDiscoteca(HashMap<String, ClienteDiscoteca> puntosEnDiscoteca) {
		
		this.puntosEnDiscoteca = puntosEnDiscoteca;
	}

	public HashMap<String, ClienteDiscoteca> getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(HashMap<String, ClienteDiscoteca> puntosTotales) {
		this.puntosTotales = puntosTotales;
	}
	public static ArrayList<Usuario> getTodos() throws SQLException, UsuarioNoExisteException, ContraseñaInvalidaException {
	    LinkedHashSet<String> columnasSacar = new LinkedHashSet<>();
	    columnasSacar.add("email");
	    columnasSacar.add("nick");

	    HashMap<String, Object> restricciones = new HashMap<>();

	    ArrayList<Usuario> clientes = new ArrayList<>();
	    ArrayList<Object> listaClientes = new ArrayList<>();
	    listaClientes = DAO.consultar("usuario", restricciones, columnasSacar);

	    for (int i = 0; i < listaClientes.size(); i += 2) {
	        Usuario cliente = new Usuario(
	            (String) listaClientes.get(i),
	            (String) listaClientes.get(i + 1)
	            
	        );
	        clientes.add(cliente);
	    }

	    return clientes;
	}
	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", qrCode=" + qrCode + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", puntosEnDiscoteca=" + puntosEnDiscoteca + ", puntosTotales=" + puntosTotales + "]";
	}

}
