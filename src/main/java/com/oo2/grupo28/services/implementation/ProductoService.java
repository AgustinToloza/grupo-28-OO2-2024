package com.oo2.grupo28.services.implementation;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Lote;
import com.oo2.grupo28.entities.Pedido;
import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.entities.Stock;
import com.oo2.grupo28.dtos.ProductoDTO;
import com.oo2.grupo28.repositories.ILoteRepository;
import com.oo2.grupo28.repositories.IPedidoRepository;
import com.oo2.grupo28.repositories.IProductoRepository;
import com.oo2.grupo28.repositories.IStockRepository;
import com.oo2.grupo28.services.IProductoService;

import jakarta.transaction.Transactional;

@Service("productoService")
public class ProductoService implements IProductoService{
	
	private IProductoRepository productoRepository;
	
	private IStockRepository stockRepository;
	
	private IPedidoRepository pedidoRepository;
	
	private ILoteRepository loteRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public ProductoService(IProductoRepository productoRepository, IStockRepository stockRepository, IPedidoRepository pedidoRepository, ILoteRepository loteRepository) {
		this.productoRepository = productoRepository;
		this.stockRepository = stockRepository;
		this.pedidoRepository = pedidoRepository;
		this.loteRepository = loteRepository;
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
			
			Producto producto = productoRepository.findById(idProducto).get();
			
			// SE ELIMINAN LOS LOTES RELACIONADOS AL PRODUCTO
			for(Lote l: loteRepository.findByProducto(idProducto)) {
				loteRepository.deleteById(l.getId());
			}
			
			// SE ELIMINAN LOS PEDIDOS RELACIONADOS AL PRODUCTO
			for(Pedido p: pedidoRepository.findByProducto(idProducto)) {
				pedidoRepository.deleteById(p.getId());
			}
			
			stockRepository.deleteById(producto.getStock().getId()); // SE ELIMINA EL STOCK RELACIONADO AL PRODUCTO
			productoRepository.deleteById(idProducto); // SE ELIMINA EL PRODUCTO
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
