package com.oo2.grupo28.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo28.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable>{
	
	public abstract Optional<Lote> findById(int id);
	
	// Todas las pedidos que tengan un producto con ese id (parámetro id)
	@Query("SELECT lot FROM Lote lot JOIN FETCH lot.producto pr WHERE pr.id = (:id)")
	public abstract List<Lote> findByProducto(int id);
	
	
	@Query("SELECT lot FROM Lote lot JOIN FETCH lot.producto pr WHERE lot.fechaRecepcion = (:fecha)")
	public abstract List<Lote> findByFecha(LocalDate fecha);
}
