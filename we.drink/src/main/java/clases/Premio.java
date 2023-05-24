package clases;

public class Premio extends SuperClaseInfo {
	private String marca;
	private double puntosNecesarios;

	

	public Premio(String nombre, String descripcion, String marca, double puntosNecesarios) {
		super(nombre, descripcion);
		this.marca = marca;
		this.puntosNecesarios = puntosNecesarios;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPuntosNecesarios() {
		return puntosNecesarios;
	}

	public void setPuntosNecesarios(double puntosNecesarios) {
		this.puntosNecesarios = puntosNecesarios;
	}

	@Override
	public String toString() {
		return "Premio [marca=" + marca + ", puntosNecesarios=" + puntosNecesarios + "]";
	}

}
