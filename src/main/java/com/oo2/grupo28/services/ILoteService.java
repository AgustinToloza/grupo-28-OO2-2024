package com.oo2.grupo28.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo28.dtos.LoteDTO;
import com.oo2.grupo28.entities.Lote;

public interface ILoteService {
	
	public List<Lote> getAll();
	
	//public LoteDTO insert(LoteDTO loteDto);
	public Lote insert(Lote lote);
	
	public Optional<Lote> findById(int id) throws Exception;
	
	public List<LoteDTO> findByProducto(int id) throws Exception;
	
	public List<LoteDTO> findByFecha(LocalDate fecha) throws Exception;
}
