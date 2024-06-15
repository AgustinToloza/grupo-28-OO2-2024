package com.oo2.grupo28.repositories;

import java.io.Serializable;

import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo28.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable>{
	
	public abstract Optional<Producto> findById(int id);
	
	public abstract Optional<Producto> findByNombre(String nombre);
	
	public abstract List<Producto> findByActivo(boolean activo);
}
