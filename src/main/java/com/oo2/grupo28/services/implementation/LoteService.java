package com.oo2.grupo28.services.implementation;

import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Lote;
import com.oo2.grupo28.dtos.LoteDTO;
import com.oo2.grupo28.repositories.ILoteRepository;
import com.oo2.grupo28.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService{
	
	private ILoteRepository loteRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public LoteService(ILoteRepository loteRepository) {
		this.loteRepository = loteRepository;
	}
	
	@Override
	public List<Lote> getAll(){
		return loteRepository.findAll();
	}
	
	@Override
	public LoteDTO insertOrUpdate(LoteDTO loteDto) {
		Lote lote = loteRepository.save(modelMapper.map(loteDto, Lote.class));
		return modelMapper.map(lote, LoteDTO.class);
	}
	
	@Override
	public boolean remove(int idLote) {
		try {
			loteRepository.deleteById(idLote);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Optional<Lote> findById(int id) throws Exception {
		return loteRepository.findById(id);
	}
}
