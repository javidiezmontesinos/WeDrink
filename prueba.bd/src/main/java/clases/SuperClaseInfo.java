package clases;

public class SuperClaseInfo {
	protected   String nombre;
	private String descripcion;
	
	public SuperClaseInfo(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "SuperClaseInfo [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
