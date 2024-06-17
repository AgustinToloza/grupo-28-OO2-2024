package com.oo2.grupo28.services;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo28.entities.Lote;

public interface ILoteService {
	
	public List<Lote> getAll();
	
	//public LoteDTO insert(LoteDTO loteDto);
	public Lote insert(Lote lote);
	
	public Optional<Lote> findById(int id) throws Exception;
}
