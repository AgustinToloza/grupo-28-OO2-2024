package com.oo2.grupo28.dtos;

import java.time.LocalDate;

import com.oo2.grupo28.entities.Pedido;
import com.oo2.grupo28.entities.Producto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoteDTO {
	
	private int id;
	
	private LocalDate fechaRecepcion;

	private int cantidadRecibida;
	
	private Producto producto;
	
	private Pedido pedido;

	public LoteDTO(int id, LocalDate fechaRecepcion, int cantidadRecibida, Producto producto, Pedido pedido) {
		super();
		this.setId(id);
		this.fechaRecepcion = fechaRecepcion;
		this.cantidadRecibida = cantidadRecibida;
		this.producto = producto;
		this.pedido = pedido;
	}
	
}
