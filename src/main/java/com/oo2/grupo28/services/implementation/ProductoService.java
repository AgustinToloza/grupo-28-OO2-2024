package com.oo2.grupo28.services.implementation;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.entities.Stock;
import com.oo2.grupo28.dtos.ProductoDTO;
import com.oo2.grupo28.repositories.IProductoRepository;
import com.oo2.grupo28.repositories.IStockRepository;
import com.oo2.grupo28.services.IProductoService;

import jakarta.transaction.Transactional;

@Service("productoService")
public class ProductoService implements IProductoService{
	
	private IProductoRepository productoRepository;
	
	private IStockRepository stockRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public ProductoService(IProductoRepository productoRepository, IStockRepository stockRepository) {
		this.productoRepository = productoRepository;
		this.stockRepository = stockRepository;
	}
	
	@Override
	public List<Producto> getAll(){
		return productoRepository.findAll();
	}
	
	//DIVIDIMOS INSERT Y UPDATE PORQUE AL INSERTAR UN PRODUCTO SE INSERTA UN STOCK RELACIONADO A ESTE PRODUCTO
	
	@Transactional
	public Producto insert(Producto producto) {
		Producto productoInsertar = productoRepository.save(producto);
		
		Stock stock = new Stock();
		stock.setCantidadActual(0);
		stock.setCantidadCritica(5);
		stock.setProducto(productoInsertar);
		stock.setAlertaReabastecimiento(true);
		
		stockRepository.save(stock);
		
		productoInsertar.setStock(stock);
		return productoRepository.save(productoInsertar);
	}
	
	@Override
	public Producto update(Producto producto) {
		return productoRepository.save(producto);
	}
	
	@Override
	public boolean remove(int idProducto) {
		boolean seBorro = false;
		if(productoRepository.findById(idProducto).isPresent()) {
			productoRepository.deleteById(idProducto);
			seBorro = true;
		}
		return seBorro;
	}
	
	@Override
	public Optional<Producto> findById(int id) throws Exception {
		return productoRepository.findById(id);
	}
	
	@Override
	public Producto findByNombre(String nombre) throws Exception{
		return productoRepository.findByNombre(nombre).orElseThrow( () -> new Exception("ERROR no existe Producto con Nombre: "+nombre));
	}
	
	@Override
	public List<ProductoDTO> findByActivo(boolean activo){
		return productoRepository.findByActivo(activo)
						.stream()
						.map(producto -> modelMapper.map(producto, ProductoDTO.class))
						.collect(Collectors.toList());
	}
}
