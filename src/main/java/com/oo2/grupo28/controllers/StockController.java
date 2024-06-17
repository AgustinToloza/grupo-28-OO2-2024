package com.oo2.grupo28.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo28.helpers.ViewRouteHelper;
import com.oo2.grupo28.dtos.StockDTO;
import com.oo2.grupo28.entities.Stock;
import com.oo2.grupo28.services.IStockService;

import jakarta.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/stocks")
public class StockController {
	
	private IStockService stockService;
	
	private ModelMapper modelMapper = new ModelMapper();

	public StockController(IStockService stockService) {
		super();
		this.stockService = stockService;
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STOCK_INDEX);
		mAV.addObject("stocks", stockService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STOCK_NEW);
		mAV.addObject("stock", new StockDTO());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("stock") StockDTO stockDTO) {
		stockService.insertOrUpdate(modelMapper.map(stockDTO, Stock.class));
		return new RedirectView(ViewRouteHelper.STOCK_ROOT);
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) throws Exception {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STOCK_UPDATE);
		StockDTO stockDTO = modelMapper.map(stockService.findById(id).get(), StockDTO.class);
		mAV.addObject("stock", stockDTO);
		return mAV;
	}
	
	@GetMapping("/by_producto/{producto_name}")
	public ModelAndView getByNombreProducto(@PathVariable("producto_name") String productoName) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STOCK_INDEX);
		try {
			mAV.addObject("stocks", stockService.findByNombreProducto(productoName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mAV;
	}
	
	@GetMapping("/informe")
	public ModelAndView verInforme(@RequestParam("nombreProducto") String nombreProducto) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.VISTA_INFORMES);
		 
        int cantidadActual;
		try {
			// Obtener la cantidad actual del producto especificado
			cantidadActual = stockService.getCantidadActual(nombreProducto);
			 // Agregar los datos al ModelAndView
	        mAV.addObject("cantidadActual", cantidadActual);
	        mAV.addObject("nombreProducto", nombreProducto);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

       
		return mAV;
	}
	
	/*@PostMapping("/update")
	public RedirectView update(@ModelAttribute("person") StockDTO stockDTO) throws Exception {
		Stock stockToUpdate = modelMapper.map(stockService.findById(stockDTO.getId()).get(), Stock.class);
		if(stockToUpdate != null ) {
			stockToUpdate.setName(stockDTO.getName()); // getOtroAtributo de Stock
			stockService.insertOrUpdate(stockToUpdate);
		}
		return new RedirectView(ViewRouteHelper.STOCK_ROOT);
	}

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		stockService.remove(id);
		return new RedirectView(ViewRouteHelper.STOCK_ROOT);
	}*/
}
