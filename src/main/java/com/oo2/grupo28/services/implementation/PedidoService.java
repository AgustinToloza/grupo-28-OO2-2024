package com.oo2.grupo28.services.implementation;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Pedido;
import com.oo2.grupo28.dtos.PedidoDTO;
import com.oo2.grupo28.entities.Lote;
import com.oo2.grupo28.repositories.ILoteRepository;
import com.oo2.grupo28.repositories.IPedidoRepository;
import com.oo2.grupo28.services.ILoteService;
import com.oo2.grupo28.services.IPedidoService;

import jakarta.transaction.Transactional;

@Service("pedidoService")
public class PedidoService implements IPedidoService{
	
	private IPedidoRepository pedidoRepository;
	private ILoteService loteService;
	private ILoteRepository loteRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public PedidoService(IPedidoRepository pedidoRepository, ILoteRepository loteRepository, ILoteService loteService) {
		this.pedidoRepository = pedidoRepository;
		this.loteRepository = loteRepository;
		this.loteService = loteService;
	}
	
	@Override
	public List<Pedido> getAll(){
		return pedidoRepository.findAll();
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	@Transactional
	public Lote insertLote(Pedido pedido) {
		
		Lote lote = new Lote(LocalDate.now(), pedido.getCantidadPedida(), pedido.getProducto(), pedido);
		
		loteService.insert(lote);
		
		return loteRepository.save(lote);
	}
	
	@Override
	public Pedido update(Pedido pedido) {
		pedido.setDadoAlta(true);
		return pedidoRepository.save(pedido);
	}
	
	@Override
	public Optional<Pedido> findById(int id) throws Exception {
		return pedidoRepository.findById(id);
	}
	
	@Override
	public List<PedidoDTO> findByProducto(String productoName) {
		return pedidoRepository.findByProducto(productoName)
				.stream()
				.map(pedido -> modelMapper.map(pedido, PedidoDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PedidoDTO> findByProducto(int id){
		return pedidoRepository.findByProducto(id)
				.stream()
				.map(pedido -> modelMapper.map(pedido, PedidoDTO.class))
				.collect(Collectors.toList());
	}
}
