package clases;

import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

public class Discoteca extends SuperClaseLugar {
	private String cif;
	private String pais;
	private String telefono;
	private TreeSet<Producto> productosDisponibles;
	private TreeMap<LocalDate, Evento> eventosDisponibles;

	public Discoteca(String nombre, String descripcion, String localidad, String direccion, String cif, String pais,
			String telefono, TreeSet<Producto> productosDisponibles, TreeMap<LocalDate, Evento> eventosDisponibles) {
		super(nombre, descripcion, localidad, direccion);
		this.cif = cif;
		this.pais = pais;
		this.telefono = telefono;
		this.productosDisponibles = productosDisponibles;
		this.eventosDisponibles = eventosDisponibles;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TreeSet<Producto> getProductosDisponibles() {
		return productosDisponibles;
	}

	public void setProductosDisponibles(TreeSet<Producto> productosDisponibles) {
		this.productosDisponibles = productosDisponibles;
	}

	public TreeMap<LocalDate, Evento> getEventosDisponibles() {
		return eventosDisponibles;
	}

	public void setEventosDisponibles(TreeMap<LocalDate, Evento> eventosDisponibles) {
		this.eventosDisponibles = eventosDisponibles;
	}

	@Override
	public String toString() {
		return "Discoteca [cif=" + cif + ", pais=" + pais + ", telefono=" + telefono + ", productosDisponibles="
				+ productosDisponibles + ", eventosDisponibles=" + eventosDisponibles + "]";
	}

}
