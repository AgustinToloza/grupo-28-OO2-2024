package com.oo2.grupo28.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.dtos.CompraDTO;

import com.oo2.grupo28.entities.Compra;

import com.oo2.grupo28.repositories.ICompraRepository;

import com.oo2.grupo28.services.ICompraService;

@Service("compraService")
public class CompraService implements ICompraService {

	private ICompraRepository compraRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public CompraService(ICompraRepository compraRepository) {
		this.compraRepository = compraRepository;
	}
	
	@Override
	public List<Compra> getAll(){
		return compraRepository.findAll();
	}
	
	@Override
	public CompraDTO insertOrUpdate( CompraDTO  compraDTO) {
		Compra compra = compraRepository.save(modelMapper.map(compraDTO, Compra.class));
		return modelMapper.map(compra, CompraDTO.class);
	}
	
	@Override
	public boolean remove(int idCompra) {
		try {
			compraRepository.deleteById(idCompra);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Optional<Compra> findById(int id) throws Exception {
		return compraRepository.findById(id);
	}


	@Override
	public List<CompraDTO> findByFechaCompra(LocalDate fecha) {
	
		return compraRepository.findByFechaCompra(fecha)
				.stream()
				.map(compra -> modelMapper.map(compra,CompraDTO.class))
				.collect(Collectors.toList());
	}
	
}
