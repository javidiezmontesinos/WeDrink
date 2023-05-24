package clases;

public class SuperClaseLugar extends SuperClaseInfo {
	private String localidad;
	private String direcion;

	
	

	public SuperClaseLugar(String nombre, String descripcion, String localidad, String direcion) {
		super(nombre, descripcion);
		this.localidad = localidad;
		this.direcion = direcion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDirecion() {
		return direcion;
	}

	public void setDirecion(String direcion) {
		this.direcion = direcion;
	}

	@Override
	public String toString() {
		return "SuperClaseLugar [localidad=" + localidad + ", direcion=" + direcion + "]";
	}

}
