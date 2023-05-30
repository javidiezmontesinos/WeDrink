package clases;

public class Logros extends SuperClaseInfo {
	private int id_logros;
	private String imagenUrl;
	private int puntosObtenidosLogros;

	public Logros(String nombre, String descripcion, int id_logros, String imagenUrl, int puntosObtenidosLogros) {
		super(nombre, descripcion);
		this.id_logros = id_logros;
		this.imagenUrl = imagenUrl;
		this.puntosObtenidosLogros = puntosObtenidosLogros;
	}

	public int getId_logros() {
		return id_logros;
	}

	public void setId_logros(int id_logros) {
		this.id_logros = id_logros;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public int getPuntosObtenidosLogros() {
		return puntosObtenidosLogros;
	}

	public void setPuntosObtenidosLogros(int puntosObtenidosLogros) {
		this.puntosObtenidosLogros = puntosObtenidosLogros;
	}

	@Override
	public String toString() {
		return "Logros [id_logros=" + id_logros + ", imagenUrl=" + imagenUrl + ", puntosObtenidosLogros="
				+ puntosObtenidosLogros + "]";
	}

}
