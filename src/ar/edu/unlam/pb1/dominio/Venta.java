package ar.edu.unlam.pb1.dominio;

import java.util.Arrays;

public class Venta {
	private static int proximoId = 1;
	private int id;
	private String vendedor;
	private String cliente;
	private Vendible[] vendibles;
	
	public Venta(String cliente, String vendedor, Vendible[] vendibles) {
		this.id = proximoId++;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.vendibles = vendibles;
	}

	public int getId() {
		return id;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getNombreCliente() {
		return cliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.cliente = nombreCliente;
	}

	public Vendible[] getVendibles() {
		return vendibles;
	}

	public void setVendibles(Vendible[] vendibles) {
		this.vendibles = vendibles;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", vendedor=" + vendedor + ", nombreCliente=" + cliente + ", vendibles="
				+ Arrays.toString(vendibles) + "]";
	}
	
	
	
}
