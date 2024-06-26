package com.oo2.grupo28.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@CreationTimestamp
	private LocalDate fechaCompra;
	
	private int cantidadComprada;
	
	private double precioVenta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Compra(int id, LocalDate fechaCompra, int cantidadComprada, double precioVenta, Producto producto,
			User user) {
		super();
		this.id = id;
		this.fechaCompra = fechaCompra;
		this.cantidadComprada = cantidadComprada;
		this.precioVenta = precioVenta;
		this.producto = producto;
		this.user = user;
	}

	public Compra(LocalDate fechaCompra, int cantidadComprada, double precioVenta, Producto producto,
			User user) {
		super();
		this.fechaCompra = fechaCompra;
		this.cantidadComprada = cantidadComprada;
		this.precioVenta = precioVenta;
		this.producto = producto;
		this.user = user;
	}
	
	

}
