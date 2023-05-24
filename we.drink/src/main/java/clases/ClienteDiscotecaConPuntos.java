package clases;

public class ClienteDiscotecaConPuntos {
	private ClienteDiscoteca cliente;
	private int puntos;

	public ClienteDiscotecaConPuntos(ClienteDiscoteca cliente, int puntos) {
		super();
		this.cliente = cliente;
		this.puntos = puntos;
	}

	public ClienteDiscoteca getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDiscoteca cliente) {
		this.cliente = cliente;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "ClienteDiscotecaConPuntos [cliente=" + cliente + ", puntos=" + puntos + "]";
	}

}
