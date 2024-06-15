package com.oo2.grupo28.services;

import java.util.List;

import java.util.Optional;

import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.dtos.ProductoDTO;

public interface IProductoService {
	
	public List<Producto> getAll();
	
	public ProductoDTO insertOrUpdate(ProductoDTO productoModel);
	
	public boolean remove(int idProducto);
	
	public Optional<Producto> findById(int id) throws Exception;
	
	public Producto findByNombre(String nombre) throws Exception;
	
	public List<ProductoDTO> findByActivo(boolean activo);
}
