package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDeVendible;

public class Vendible {

	// TODO: Representa la abstracción de algo posible de ser vendido en la tienda.
	// Para este producto software podrá ser de tipo PRODUCTO o SERVICIO. Completar
	// el constructor, getters, setters y otros métodos necesarios.
	
	private String codigo;
	private TipoDeVendible tipoDeVendible;
	private int stock;
	private double precio;

	public static final int CANTIDAD_MAXIMA_PRODUCTOS = 10;

	public Vendible(String codigo, TipoDeVendible tipoDeVendible, int stock, double precio) {
		this.setCodigo(codigo);
		this.setTipoDeVendible(tipoDeVendible);
		this.setStock(stock);
		this.setPrecio(precio);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoDeVendible getTipoDeVendible() {
		return tipoDeVendible;
	}

	public void setTipoDeVendible(TipoDeVendible tipoDeVendible) {
		this.tipoDeVendible = tipoDeVendible;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "codigo: "+this.codigo+", tipo: "+this.tipoDeVendible+", stock: "+this.stock+", precio: "+this.precio;
	}
	
}
