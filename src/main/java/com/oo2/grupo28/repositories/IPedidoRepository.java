package com.oo2.grupo28.repositories;

import java.io.Serializable;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo28.entities.Pedido;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable>{
	
	public abstract Optional<Pedido> findById(int id);
	
}

