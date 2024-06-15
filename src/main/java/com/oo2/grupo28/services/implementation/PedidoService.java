package com.oo2.grupo28.services.implementation;

import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Pedido;
import com.oo2.grupo28.dtos.PedidoDTO;
import com.oo2.grupo28.repositories.IPedidoRepository;
import com.oo2.grupo28.services.IPedidoService;

@Service("pedidoService")
public class PedidoService implements IPedidoService{
	
	private IPedidoRepository pedidoRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public PedidoService(IPedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	@Override
	public List<Pedido> getAll(){
		return pedidoRepository.findAll();
	}
	
	@Override
	public PedidoDTO insertOrUpdate(PedidoDTO pedidoDto) {
		Pedido pedido = pedidoRepository.save(modelMapper.map(pedidoDto, Pedido.class));
		return modelMapper.map(pedido, PedidoDTO.class);
	}
	
	@Override
	public boolean remove(int idPedido) {
		try {
			pedidoRepository.deleteById(idPedido);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Optional<Pedido> findById(int id) throws Exception {
		return pedidoRepository.findById(id);
	}
}
