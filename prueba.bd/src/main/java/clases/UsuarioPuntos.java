package clases;

import java.util.List;

public class UsuarioPuntos {
	private Usuario usuarioPnts;
	private int puntosTotales;

	public UsuarioPuntos(Usuario usuarioPnts, int puntosTotales) {
		super();
		this.usuarioPnts = usuarioPnts;
		this.puntosTotales = puntosTotales;
	}

	public void puntosTotalesUsuario(List<ClienteDiscoteca> clienteDiscotecas) {
		puntosTotales = 0;
		for (ClienteDiscoteca cliente : clienteDiscotecas) {
			if (cliente.getUsuarioCliente().equals(this.usuarioPnts)) {
				puntosTotales += cliente.getPuntosAcumuladosDiscoteca(puntosTotales);
			}
		}
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
		this.puntosTotales = puntosTotales;
	}

	@Override
	public String toString() {
		return "UsuarioPuntos [usuarioPnts=" + usuarioPnts + ", puntosTotales=" + puntosTotales + "]";
	}

}
