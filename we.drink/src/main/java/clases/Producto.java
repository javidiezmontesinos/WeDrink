package clases;

public class Producto extends SuperClaseInfo {
	private int puntosPorCompra;
	private double precioProducto;

	
	public Producto(String nombre, String descripcion, int puntosPorCompra, double precioProducto) {
		super(nombre, descripcion);
		this.puntosPorCompra = puntosPorCompra;
		this.precioProducto = precioProducto;
	}


	public int getPuntosPorCompra() {
		return puntosPorCompra;
	}

	public void setPuntosPorCompra(int puntosPorCompra) {
		this.puntosPorCompra = puntosPorCompra;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	@Override
	public String toString() {
		return "Producto [puntosPorCompra=" + puntosPorCompra + ", precioProducto=" + precioProducto + "]";
	}

}
