package com.oo2.grupo28.services.implementation;

import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Lote;
import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.repositories.ILoteRepository;
import com.oo2.grupo28.services.IProductoService;

import jakarta.transaction.Transactional;

import com.oo2.grupo28.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService{
	
	private ILoteRepository loteRepository;
	private IProductoService productoService;
	
	private ModelMapper modelMapper = new ModelMapper();

	public LoteService(ILoteRepository loteRepository, IProductoService productoService) {
		this.loteRepository = loteRepository;
		this.productoService = productoService;
	}
	
	@Override
	public List<Lote> getAll(){
		return loteRepository.findAll();
	}
	
	@Transactional
	public Lote insert(Lote lote) {
		Producto producto = lote.getProducto();
		
		producto.getStock().setCantidadActual(producto.getStock().getCantidadActual() + lote.getCantidadRecibida());
		
		productoService.update(producto);
		
		return loteRepository.save(lote);
	}
	
	@Override
	public Optional<Lote> findById(int id) throws Exception {
		return loteRepository.findById(id);
	}
}
