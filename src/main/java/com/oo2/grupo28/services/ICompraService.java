package com.oo2.grupo28.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo28.dtos.CompraDTO;

import com.oo2.grupo28.entities.Compra;


public interface ICompraService {

	public List<Compra> getAll();
	
	public CompraDTO insertOrUpdate(CompraDTO compraModel);
	
	public boolean remove(int idCompra);
	
	public Optional<Compra> findById(int id) throws Exception;
	
	public List<CompraDTO> findByFechaCompra(LocalDate fecha);
}
