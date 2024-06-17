package com.oo2.grupo28.entities;

import jakarta.persistence.CascadeType;
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
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	
	private double precio;

	private String codigo;
	
	private boolean activo;
	
	@OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="stock_id", nullable=false)
	private Stock stock;

	public Producto(int id, String nombre, double precio, String codigo, boolean activo, Stock stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = codigo;
		this.activo = activo;
		this.stock = stock;
	}

	public Producto(String nombre, double precio, String codigo, boolean activo, Stock stock) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = codigo;
		this.activo = activo;
		this.stock = stock;
	}
	
}