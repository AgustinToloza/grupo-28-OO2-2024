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
	
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("pedido") PedidoDTO pedidoDTO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			// Si hay errores de validación, vuelve a mostrar el formulario con los errores
			model.addAttribute("pedido", pedidoDTO);
	        return ViewRouteHelper.PEDIDO_NEW;
		}
		
		// Si no hay errores, procede con la inserción/actualización del pedido
		Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);
		pedidoService.insert(pedido);
	    
	    // Redirige a la vista principal de pedidos después de guardar correctamente
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
