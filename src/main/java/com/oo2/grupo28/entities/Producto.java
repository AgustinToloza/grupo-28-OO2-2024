package com.oo2.grupo28.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	public Producto(int id, String nombre, double precio, String codigo, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = codigo;
		this.activo = activo;
	}

	public Producto(String nombre, double precio, String codigo, boolean activo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = codigo;
		this.activo = activo;
	}
	
}