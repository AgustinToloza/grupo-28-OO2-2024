package com.oo2.grupo28.services;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo28.entities.Lote;
import com.oo2.grupo28.dtos.LoteDTO;

public interface ILoteService {
	
	public List<Lote> getAll();
	
	public LoteDTO insertOrUpdate(LoteDTO loteDto);
	
	public boolean remove(int idLote);
	
	public Optional<Lote> findById(int id) throws Exception;
}
