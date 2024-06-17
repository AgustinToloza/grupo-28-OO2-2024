package com.oo2.grupo28.repositories;

import java.io.Serializable;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo28.entities.Stock;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable>{
	
	public abstract Optional<Stock> findById(int id);
	
	// El STOCK que TENGA el PRODUCTO con ese nombre (parámetro nombre)
	@Query("SELECT s FROM Stock s JOIN FETCH s.producto p WHERE p.nombre = (:nombre)")
	public abstract Optional<Stock> findByNombreProducto(String nombre);
	
}
