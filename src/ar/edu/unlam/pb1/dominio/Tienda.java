package ar.edu.unlam.pb1.dominio;

import java.util.Random;

import ar.edu.unlam.pb1.dominio.enums.TipoDeVendible;

public class Tienda {
	
	static Random random = new Random();

	private String codigo;
	private String razonSocial;
	private Vendible[] vendibles;
	private Venta[] ventas;

	public Tienda(String codigo, String razonSocial) {
		this.codigo = codigo;
		this.razonSocial = razonSocial;
		this.vendibles = new Vendible[100];
		this.ventas = new Venta[1000];
		this.inicializarTienda(); // Esta linea debe ejecutarse en el constructor
	}

	public String getCodigo() {
		return codigo;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public Vendible[] getVendibles() {
		return vendibles;
	}
	
	public Venta[] getVentas() {
		return ventas;
	}

	public Vendible[] buscarVendiblesCuyoCodigoIniciaConTexto(String textoABuscar) {
		// TODO: Se debera devolver un array de vendibles cuyo codigo comienza con el
		// texto indicado indicado para buscar.
		Vendible vendiblesBuscados[] = new Vendible[this.vendibles.length];
		int i = 0;
		for(Vendible vendible: this.vendibles) {
			if(vendible!=null && vendible.getCodigo().charAt(0)==textoABuscar.toUpperCase().charAt(0)) {
				vendiblesBuscados[i] = vendible;
				i++;
			}
		}
		return vendiblesBuscados;
	}

	public Vendible obtenerVendiblePorCodigo(String codigo) {
		// TODO: Se debera encontrar y devolver un vendible en el array de vendibles que
		// cumpla con el codigo suministrado. En caso de no encontrarlo, se debera
		// devolver null.
		Vendible vendibleBuscado = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<this.vendibles.length) {
			if(this.vendibles[i]!=null && this.vendibles[i].getCodigo().equals(codigo.toUpperCase())) {
				vendibleBuscado = this.vendibles[i];
				encontrado = true;
			}
			i++;
		}
		return vendibleBuscado;
	}

	public boolean crearVentaDeProductosOServicios(String cliente, String vendedor, Vendible[] vendiblesParaVenta) {
		// TODO: Se debera instanciar una venta utilizando los parametros y luego
		// agregar la misma al array de ventas.
		// El metodo debe devolver verdadero en caso de completar la operacion o falso
		// en caso de no lograrlo por alguna razon.
		Venta venta = new Venta(cliente, vendedor, vendiblesParaVenta);
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i<this.ventas.length) {
			if(this.ventas[i]==null) {
				this.ventas[i] = venta;
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}

	public int obtenerCantidadDeServiciosEnVentas() {
		// TODO: Se debera devolver la cantidad de servicios presentes en todas las
		// ventas.
		// Cada venta contiene los vendibles (PRODUCTO o SERVICIO) que se incluyeron en
		// la misma. De estos, nos interesa saber cuantos vendibles son de tipo
		// "SERVICIO". Es
		// importante mirar los vendibles en todas las ventas de la tienda.
		int cantidad = 0;
		for(Venta venta: this.ventas) {
			if(venta!=null) {
				for(Vendible vendible: venta.getVendibles()) {
					if(vendible!=null && vendible.getTipoDeVendible().equals(TipoDeVendible.SERVICIO)) {
						cantidad+=1;
					}
				}				
			}	
		}
		return cantidad;
	}

	public Vendible[] obtenerProductosConStockMaximoOrdenadosPorPrecioDescendente() {
		// TODO: Armar y devolver un array de vendibles de tipo producto, cuyo stock sea
		// el maximo admitido por la tienda (Revisar la constante). El array a devolver
		// debera estar ordenado por precio de manera descendente (ver
		// ordenarVendiblesPorPrecioPrescendente()).
		Vendible[] vendiblesOrdenados = new Vendible[this.vendibles.length];
		int i = 0;
		for(Vendible vendible: this.vendibles) {
			if(vendible.getTipoDeVendible().equals(TipoDeVendible.PRODUCTO) && vendible.getStock()==Vendible.CANTIDAD_MAXIMA_PRODUCTOS) {
				vendiblesOrdenados[i] = vendible;
				i++;
			}
		}
		this.ordenarVendiblesPorPrecioPrescendente(vendiblesOrdenados);
		return vendiblesOrdenados;
	}

	private void ordenarVendiblesPorPrecioPrescendente(Vendible[] vendibles) {
		// TODO: Ordenar el array de vendibles suministrado por precio del vendible, de
		// manera descendente.
		Vendible aux = null;
		for(int i = 1; i<vendibles.length; i++) {
			for(int j = 0; j<vendibles.length-1; j++) {
				if(vendibles[j]!=null && vendibles[j+1]!=null && vendibles[j].getPrecio()<vendibles[j+1].getPrecio()) {
					aux = vendibles[j+1];
					vendibles[j+1] = vendibles[j];
					vendibles[j] = aux;
				}
			}
			
		}
	}

	private void inicializarTienda() {
		// TODO: Se deberan generar los vendibles que estaran disponibles para que la
		// tienda pueda operar.
		// Es necesario asignar cada posicion del array de vendibles con una instancia
		// de vendible. En las posiciones pares, se debera colocar un vendible de tipo
		// PRODUCTO, mientras que en las posiciones impares, se debera colocar un
		// vendible de tipo SERVICIO.
		// El codigo de los vendibles debe ser la letra 'P' seguido de un numero para
		// los productos. Para los servicios, el codigo del vendible debe ser una letra
		// 'S' seguido de un numero.
		// El precio de los productos se debera generar considerando que no sean todos
		// iguales (nos sirve para ordenar). El precio de los servicios puede ser fijo.
		// Los servicios no tienen cantidad maxima, en este atributo se debera ingresar un cero.
		// Ejemplos: codigos de productos: "P0", "P1", etc.
		// Ejemplos: codigos de servicios: "S0", "S1", etc.
		int contadorProducto = 0;
		int contadorServicio = 0;
		for(int i=0; i<this.vendibles.length; i++) {
			if(i%2 == 0) {
				String codigoProducto = "P"+contadorProducto;
				this.vendibles[i] = new Vendible(codigoProducto, TipoDeVendible.PRODUCTO, Vendible.CANTIDAD_MAXIMA_PRODUCTOS, this.precioRandom());
				contadorProducto++;
			}else {
				String codigoServicio = "S"+contadorServicio;
				this.vendibles[i] = new Vendible(codigoServicio, TipoDeVendible.SERVICIO, 0, 25.0);
				contadorServicio++;
			}
		}
	}
	
	private double precioRandom() {
		double precio = random.nextInt(100)+1;
		return precio;
	}

}
