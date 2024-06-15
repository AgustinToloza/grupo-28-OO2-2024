package com.oo2.grupo28.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Lote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate fechaRecepcion;

	private int cantidadRecibida;

	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pedido_id", referencedColumnName="id")
	private Pedido pedido;

	public Lote(int id, LocalDate fechaRecepcion, int cantidadRecibida, Producto producto, Pedido pedido) {
		super();
		this.id = id;
		this.fechaRecepcion = fechaRecepcion;
		this.cantidadRecibida = cantidadRecibida;
		this.producto = producto;
		this.pedido = pedido;
	}

	public Lote(LocalDate fechaRecepcion, int cantidadRecibida, Producto producto, Pedido pedido) {
		super();
		this.fechaRecepcion = fechaRecepcion;
		this.cantidadRecibida = cantidadRecibida;
		this.producto = producto;
		this.pedido = pedido;
	}
	
}