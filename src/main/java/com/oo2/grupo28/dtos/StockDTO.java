package com.oo2.grupo28.dtos;

import com.oo2.grupo28.entities.Producto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class StockDTO {
	
	private int id;

	private int cantidadActual;
	
	private int cantidadCritica;

	private Producto producto;
	
	private boolean alertaReabastecimiento;

	public StockDTO(int id, int cantidadActual, int cantidadCritica, Producto producto,
			boolean alertaReabastecimiento) {
		this.setId(id);
		this.cantidadActual = cantidadActual;
		this.cantidadCritica = cantidadCritica;
		this.producto = producto;
		this.alertaReabastecimiento = alertaReabastecimiento;
	}
	
}
