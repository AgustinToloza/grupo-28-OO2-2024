package com.oo2.grupo28.services.implementation;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.dtos.ProductoDTO;
import com.oo2.grupo28.repositories.IProductoRepository;
import com.oo2.grupo28.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService{
	
	private IProductoRepository productoRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public ProductoService(IProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	@Override
	public List<Producto> getAll(){
		return productoRepository.findAll();
	}
	
	@Override
	public ProductoDTO insertOrUpdate(ProductoDTO productoModel) {
		Producto producto = productoRepository.save(modelMapper.map(productoModel, Producto.class));
		return modelMapper.map(producto, ProductoDTO.class);
	}
	
	@Override
	public boolean remove(int idProducto) {
		try {
			productoRepository.deleteById(idProducto);
			return true;
		}catch(Exception e){
			return false;
		}
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
