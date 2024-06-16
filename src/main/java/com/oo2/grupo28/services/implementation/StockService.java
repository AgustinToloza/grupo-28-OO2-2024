package com.oo2.grupo28.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.grupo28.entities.Stock;
import com.oo2.grupo28.dtos.StockDTO;
import com.oo2.grupo28.repositories.IStockRepository;
import com.oo2.grupo28.services.IStockService;

@Service("stockService")
public class StockService implements IStockService{
	
	private IStockRepository stockRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public StockService(IStockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
	@Override
	public List<Stock> getAll(){
		return stockRepository.findAll();
	}
	
	@Override
	public Stock insertOrUpdate(Stock stock) {
		return stockRepository.save(stock);
	}
	
	@Override
	public boolean remove(int idStock) {
		try {
			stockRepository.deleteById(idStock);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Optional<Stock> findById(int id) throws Exception{
		return stockRepository.findById(id);
	}
	
	@Override
	public StockDTO findByNombreProducto(String nombre) throws Exception{
		return (StockDTO) stockRepository.findByNombreProducto(nombre)
				.stream()
				.map(stock -> modelMapper.map(stock, StockDTO.class));
	}
	@Override
	 public int getCantidadActual(String nombreProducto) throws Exception {
	        return stockRepository.findByNombreProducto(nombreProducto).stream()
	                .mapToInt(Stock::getCantidadActual)
	                .sum();
	    }
}
