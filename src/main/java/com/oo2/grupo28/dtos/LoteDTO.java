package com.oo2.grupo28.dtos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoteDTO {
	
	private int id;
	
	private LocalDate fechaRecepcion;

	private int cantidadRecibida;

	public LoteDTO(int id, LocalDate fechaRecepcion, int cantidadRecibida) {
		super();
		this.setId(id);
		this.fechaRecepcion = fechaRecepcion;
		this.cantidadRecibida = cantidadRecibida;
	}
	
}
