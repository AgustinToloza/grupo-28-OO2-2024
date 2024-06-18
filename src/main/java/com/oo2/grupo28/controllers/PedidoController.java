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
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo28.dtos.PedidoDTO;
import com.oo2.grupo28.entities.Lote;
import com.oo2.grupo28.entities.Pedido;
import com.oo2.grupo28.helpers.ViewRouteHelper;
import com.oo2.grupo28.services.IPedidoService;

import jakarta.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/pedidos")
public class PedidoController {
	
	private IPedidoService pedidoService;
	
	private ModelMapper modelMapper = new ModelMapper();

	public PedidoController(IPedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_INDEX);
		mAV.addObject("pedidos", pedidoService.getAll());
		mAV.addObject("pedido", new PedidoDTO());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);
		mAV.addObject("pedido", new PedidoDTO());
		return mAV;
	}
	
	@PostMapping("/new_pedido")
	public String createPedido(@Valid @ModelAttribute("pedido") PedidoDTO pedidoDTO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("pedido", pedidoDTO);
	        return ViewRouteHelper.PEDIDO_NEW;
		}
		
		Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);
		pedidoService.insert(pedido);
	    
	    return "redirect:" + ViewRouteHelper.PEDIDO_ROOT;
	}
	
	@PostMapping("/new_lote/{id}")
	public String createLote(@PathVariable("id") int id, @ModelAttribute("pedido") PedidoDTO pedidoDTO, BindingResult bindingResult, Model model) throws Exception {
		
		Pedido pedido = modelMapper.map(pedidoService.findById(id).get(), Pedido.class);
	    
		Lote lote = pedidoService.insertLote(pedido);
		
		pedido.setLote(lote);
		pedidoService.update(pedido);
	    
	    
	    return "redirect:" + ViewRouteHelper.PEDIDO_ROOT;
	}
	
	@GetMapping("/by_producto/{producto_nombre}")
	public ModelAndView getByNombreProducto(@PathVariable("producto_nombre") String productoName) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_INDEX);
		try {
			mAV.addObject("pedidos", pedidoService.findByProducto(productoName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mAV;
	}
	
}
