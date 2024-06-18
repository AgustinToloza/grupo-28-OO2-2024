package com.oo2.grupo28.dtos;

import com.oo2.grupo28.entities.Stock;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductoDTO {
	
	private int id;
	
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	
	@NotNull(message = "El precio es obligatorio")
	@Min(value = 0, message = "El precio debe ser mayor o igual a 0")
	private double precio;
	
	@NotBlank(message = "El código es obligatorio")
	@Size(min=2, max=5, message = "El código debe tener entre 2 y 5 caracteres")
	private String codigo;
	
	private boolean activo;
	
	private Stock stock;

	public ProductoDTO(int id, String nombre, double precio, @Size(min = 2, max = 5) String codigo,
			boolean activo, Stock stock) {
		this.setId(id);
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = codigo;
		this.activo = activo;
		this.stock = stock;
	}
	
	
}