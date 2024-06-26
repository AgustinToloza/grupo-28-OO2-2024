package com.oo2.grupo28.dtos;

import com.oo2.grupo28.entities.Lote;
import com.oo2.grupo28.entities.Producto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PedidoDTO {
	
	private int id;
	
	@NotBlank(message = "El proveedor es obligatorio")
	@Size(min=2, max=20)
	private String proveedor;
	
	@NotNull(message = "La cantidad es obligatoria")
	@Min(1)
	private int cantidadPedida;
	
	private boolean dadoAlta;
	
	@NotNull(message = "El producto es obligatorio")
	private Producto producto;
	
	private Lote lote;

	public PedidoDTO(int id, String proveedor, int cantidadPedida, Producto producto, Lote lote){
		super();
		this.setId(id);
		this.proveedor = proveedor;
		this.cantidadPedida = cantidadPedida;
		this.producto = producto;
		this.lote = lote;
		this.dadoAlta = false;
	}
	
}