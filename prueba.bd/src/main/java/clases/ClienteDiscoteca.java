package clases;

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
