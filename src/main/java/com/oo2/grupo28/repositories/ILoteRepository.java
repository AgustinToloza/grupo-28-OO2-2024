package com.oo2.grupo28.repositories;

import java.io.Serializable;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo28.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable>{
	
	public abstract Optional<Lote> findById(int id);
	
}
