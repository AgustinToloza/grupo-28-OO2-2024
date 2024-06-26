package com.oo2.grupo28.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor

public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cantidadActual;
	
	private int cantidadCritica;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_id", nullable=false)
	private Producto producto;
	
	private boolean alertaReabastecimiento;

	public Stock(int id, int cantidadActual, int cantidadCritica, Producto producto, boolean alertaReabastecimiento) {
		super();
		this.id = id;
		this.cantidadActual = cantidadActual;
		this.cantidadCritica = cantidadCritica;
		this.producto = producto;
		this.alertaReabastecimiento = alertaReabastecimiento;
	}

	public Stock(int cantidadActual, int cantidadCritica, Producto producto, boolean alertaReabastecimiento) {
		super();
		this.cantidadActual = cantidadActual;
		this.cantidadCritica = cantidadCritica;
		this.producto = producto;
		this.alertaReabastecimiento = alertaReabastecimiento;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}
	
	
	
}
