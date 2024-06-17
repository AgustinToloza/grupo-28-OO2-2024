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
import com.oo2.grupo28.repositories.IPedidoRepository;
import com.oo2.grupo28.services.ILoteService;
import com.oo2.grupo28.services.IPedidoService;

import jakarta.transaction.Transactional;

@Service("pedidoService")
public class PedidoService implements IPedidoService{
	
	private IPedidoRepository pedidoRepository;
	private ILoteService loteService;
	
	private ModelMapper modelMapper = new ModelMapper();

	public PedidoService(IPedidoRepository pedidoRepository, ILoteService loteService) {
		this.pedidoRepository = pedidoRepository;
		this.loteService = loteService;
	}
	
	@Override
	public List<Pedido> getAll(){
		return pedidoRepository.findAll();
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		Pedido pedidoInsertar = pedidoRepository.save(pedido);
		
		Lote lote = new Lote(LocalDate.now(), pedidoInsertar.getCantidadPedida(), pedidoInsertar.getProducto(), pedidoInsertar);
		
		loteService.insert(lote);
		pedidoInsertar.setLote(lote);
		
		return pedidoRepository.save(pedidoInsertar);
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
}
