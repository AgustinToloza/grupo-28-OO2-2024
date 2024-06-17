package com.oo2.grupo28.services;

import java.util.List;
import java.util.Optional;

import com.oo2.grupo28.entities.Pedido;
import com.oo2.grupo28.dtos.PedidoDTO;

public interface IPedidoService {
	
	public List<Pedido> getAll();
	
	public Pedido insert(Pedido pedido);
	
	public Optional<Pedido> findById(int id) throws Exception;
	
	public List<PedidoDTO> findByProducto(String nombreProducto) throws Exception;;
}

