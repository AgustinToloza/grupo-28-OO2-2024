package com.oo2.grupo28.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CompraDTO {
	private int id;
private LocalDate fechaCompra;
	
	private int cantidadComprada;
	
	private double precioVenta;
	private Set<Producto> productos=new HashSet<>();
	private User user;
	public CompraDTO(int id, LocalDate fechaCompra, int cantidadComprada, double precioVenta, Set<Producto> productos,
			User user) {
		super();
		this.setId(id);
		this.fechaCompra = fechaCompra;
		this.cantidadComprada = cantidadComprada;
		this.precioVenta = precioVenta;
		this.productos = productos;
		this.user = user;
	}
	
	

}
