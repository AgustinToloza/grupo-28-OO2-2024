package com.oo2.grupo28.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PedidoDTO {
	
	private int id;
	
	@Size(min=2, max=20)
	private String proveedor;
	
	@Min(1)
	private int cantidadPedida;

	public PedidoDTO(int id, String proveedor, int cantidadPedida) {
		super();
		this.setId(id);
		this.proveedor = proveedor;
		this.cantidadPedida = cantidadPedida;
	}
	
}