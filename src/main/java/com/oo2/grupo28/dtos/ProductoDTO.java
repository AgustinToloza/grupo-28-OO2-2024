package com.oo2.grupo28.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductoDTO {
	
	private int id;

	private String nombre;

	private double precio;
	
	@Size(min=2, max=5)
	private String codigo;
	
	private boolean activo;

	public ProductoDTO(int id, String nombre, double precio, @Size(min = 2, max = 5) String codigo,
			boolean activo) {
		this.setId(id);
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = codigo;
		this.activo = activo;
	}
	
	
}