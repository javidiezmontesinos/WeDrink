package clases;

public class ClienteDiscoteca {
	private int puntosacumulados;
	private Discoteca discoteca;

	public ClienteDiscoteca(int puntosacumulados, Discoteca discoteca) {
		super();
		this.puntosacumulados = puntosacumulados;
		this.discoteca = discoteca;
	}
	

	public int getPuntosacumulados() {
		return puntosacumulados;
	}

	public void setPuntosacumulados(int puntosacumulados) {
		this.puntosacumulados = puntosacumulados;
	}

	public Discoteca getDiscoteca() {
		return discoteca;
	}

	public void setDiscoteca(Discoteca discoteca) {
		this.discoteca = discoteca;
	}

	@Override
	public String toString() {
		return "ClienteDiscoteca [puntosacumulados=" + puntosacumulados + ", discoteca=" + discoteca + "]";
	}


	public int getPuntos() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void setPuntos(int puntosRestantes) {
		// TODO Auto-generated method stub
		
	}

}
