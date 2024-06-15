package com.oo2.grupo28.entities;

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
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String proveedor;

	private int cantidadPedida;

	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User usuario;
	
	@OneToOne(mappedBy="pedido")
	private Lote lote;

	public Pedido(int id, String proveedor, int cantidadPedida, Producto producto, User usuario, Lote lote) {
		super();
		this.id = id;
		this.proveedor = proveedor;
		this.cantidadPedida = cantidadPedida;
		this.producto = producto;
		this.usuario = usuario;
	}

	public Pedido(String proveedor, int cantidadPedida, Producto producto, User usuario, Lote lote) {
		super();
		this.proveedor = proveedor;
		this.cantidadPedida = cantidadPedida;
		this.producto = producto;
		this.usuario = usuario;
	}
	
}