package com.oo2.grupo28.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
import com.oo2.grupo28.services.ILoteService;
import com.oo2.grupo28.services.IPedidoService;
import com.oo2.grupo28.services.IProductoService;
import com.oo2.grupo28.services.IStockService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/reportes")
public class ReporteController {
	
	private IStockService stockService;
	private IProductoService productoService;
	private ILoteService loteService;
	private IPedidoService pedidoService;
	
	private ModelMapper modelMapper = new ModelMapper();

	public ReporteController(IStockService stockService, IProductoService productoService, ILoteService loteService, IPedidoService pedidoService) {
		super();
		this.stockService = stockService;
		this.productoService = productoService;
		this.loteService = loteService;
		this.pedidoService = pedidoService;
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.REPORTE_INDEX);
		return mAV;
	}
	
	@GetMapping("/lote")
	public ModelAndView informeLote() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.REPORTE_LOTE);
		 
        

		return mAV;
	}
	
	/*

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
	
	*/
	
}
