package com.oo2.grupo28.services;

import java.util.List;

import java.util.Optional;

import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.entities.Stock;
import com.oo2.grupo28.dtos.StockDTO;

public interface IStockService {
	
	public List<Stock> getAll();
	
	public Stock insertOrUpdate(Stock stock);
	
	public boolean remove(int idStock);
	
	public Optional<Stock> findById(int id) throws Exception;
	
	public StockDTO findByNombreProducto(String nombre) throws Exception;
	
	public int getCantidadActual(String nombreProducto) throws Exception;
}
