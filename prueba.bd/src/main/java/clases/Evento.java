package clases;

import java.time.LocalDate;
import java.util.HashSet;

public class Evento extends SuperClaseLugar {
	private LocalDate fecha;
	private int puntosPorAsistir;
	private HashSet<Usuario> asistentes;
	private double precioEvento;

	public Evento(String nombre, String descripcion, String localidad, String direccion, LocalDate fecha,
			int puntosPorAsistir, HashSet<Usuario> asistentes, double precioEvento) {
		super(nombre, descripcion, localidad, direccion);
		this.fecha = fecha;
		this.puntosPorAsistir = puntosPorAsistir;
		this.asistentes = asistentes;
		this.precioEvento = precioEvento;
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

	@Override
	public String toString() {
		return "Evento [fecha=" + fecha + ", puntosPorAsistir=" + puntosPorAsistir + ", asistentes=" + asistentes
				+ ", precioEvento=" + precioEvento + "]";
	}

}
