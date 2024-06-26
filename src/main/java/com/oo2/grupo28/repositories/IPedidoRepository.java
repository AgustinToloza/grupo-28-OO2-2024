package com.oo2.grupo28.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo28.entities.Pedido;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable>{
	
	public abstract Optional<Pedido> findById(int id);
	
	// Todas las pedidos que tengan un producto con ese nombre (parámetro name)
	@Query("SELECT pe FROM Pedido pe JOIN FETCH pe.producto pr WHERE pr.nombre = (:nombreProducto)")
	public abstract List<Pedido> findByProducto(String nombreProducto);
	
	// Todas las pedidos que tengan un producto con ese id (parámetro id)
	@Query("SELECT pe FROM Pedido pe JOIN FETCH pe.producto pr WHERE pr.id = (:id)")
	public abstract List<Pedido> findByProducto(int id);
	
}

