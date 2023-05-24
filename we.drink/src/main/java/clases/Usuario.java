package clases;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Usuario extends SuperClaseLugar {
	private String nick;
	private BufferedImage qrCode;
	private String apellidos;
	private String correo;
	private HashMap<String, ClienteDiscoteca> puntosEnDiscoteca;
	private HashMap<String, ClienteDiscoteca> puntosTotales;

	
	

	public Usuario(String nombre, String descripcion, String localidad, String direcion, String nick,
			BufferedImage qrCode, String apellidos, String correo, HashMap<String, ClienteDiscoteca> puntosEnDiscoteca,
			HashMap<String, ClienteDiscoteca> puntosTotales) {
		super(nombre, descripcion, localidad, direcion);
		this.nick = nick;
		this.qrCode = qrCode;
		this.apellidos = apellidos;
		this.correo = correo;
		this.puntosEnDiscoteca = puntosEnDiscoteca;
		this.puntosTotales = puntosTotales;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public BufferedImage getQrCode() {
		return qrCode;
	}

	public void setQrCode(BufferedImage qrCode) {
		this.qrCode = qrCode;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
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

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", qrCode=" + qrCode + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", puntosEnDiscoteca=" + puntosEnDiscoteca + ", puntosTotales=" + puntosTotales + "]";
	}

}
