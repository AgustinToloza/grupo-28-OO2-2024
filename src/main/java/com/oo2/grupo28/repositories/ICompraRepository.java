package com.oo2.grupo28.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo28.entities.Compra;


@Repository("compraRepository")
public interface ICompraRepository extends JpaRepository<Compra, Serializable>{
	
	public abstract Optional<Compra> findById(int id);
	
	@Query("SELECT c FROM Compra c WHERE c.fechaCompra = :fecha")
    List<Compra> findByFechaCompra(@Param("fecha") LocalDate fecha);

}
