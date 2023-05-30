package clases;

import java.time.LocalDate;
import java.util.HashSet;

public class Evento extends SuperClaseLugar {
	private LocalDate fecha;
	private int puntosPorAsistir;
	private HashSet<Usuario> asistentes;
	private double precioEvento;
	private String imagenUrl;

	
	public Evento(String nombre, String descripcion, String localidad, String direcion, LocalDate fecha,
			int puntosPorAsistir, HashSet<Usuario> asistentes, double precioEvento, String imagenUrl) {
		super(nombre, descripcion, localidad, direcion);
		this.fecha = fecha;
		this.puntosPorAsistir = puntosPorAsistir;
		this.asistentes = asistentes;
		this.precioEvento = precioEvento;
		this.imagenUrl = imagenUrl;
	}

	public Evento(String nombre,String descripcion,String localidad,String fecha) {
		super(fecha, descripcion, localidad, fecha);
		this.nombre = nombre;
        this.fecha = LocalDate.parse(fecha);
    }
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getPuntosPorAsistir() {
		return puntosPorAsistir;
	}

	public void setPuntosPorAsistir(int puntosPorAsistir) {
		this.puntosPorAsistir = puntosPorAsistir;
	}

	public HashSet<Usuario> getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(HashSet<Usuario> asistentes) {
		this.asistentes = asistentes;
	}

	public double getPrecioEvento() {
		return precioEvento;
	}

	public void setPrecioEvento(double precioEvento) {
		this.precioEvento = precioEvento;
	}
	

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	@Override
	public String toString() {
		return "Evento [fecha=" + fecha + ", puntosPorAsistir=" + puntosPorAsistir + ", asistentes=" + asistentes
				+ ", precioEvento=" + precioEvento + ", imagenUrl=" + imagenUrl + "]";
	}

	

}
