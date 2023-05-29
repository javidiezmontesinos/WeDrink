package clases;

public class SuperClaseLugar extends SuperClaseInfo {
	protected  String localidad;
	protected  String direccion;
	
	public SuperClaseLugar(String nombre, String descripcion, String localidad, String direcion) {
		super(nombre, descripcion);
		this.localidad = localidad;
		this.direccion = direcion;
	}
	
	
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getDirecion() {
		return direccion;
	}
	public void setDirecion(String direcion) {
		this.direccion = direcion;
	}
	@Override
	public String toString() {
		return "SuperClaseLugar [localidad=" + localidad + ", direcion=" + direccion + "]";
	}


}
