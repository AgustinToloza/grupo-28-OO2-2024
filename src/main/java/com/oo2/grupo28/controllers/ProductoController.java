package com.oo2.grupo28.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo28.helpers.ViewRouteHelper;
import com.oo2.grupo28.dtos.ProductoDTO;
import com.oo2.grupo28.services.IProductoService;

import jakarta.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/productos")
public class ProductoController {
	
	private IProductoService productoService;

	public ProductoController(IProductoService productoService) {
		super();
		this.productoService = productoService;
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_INDEX);
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("producto", new ProductoDTO());
		return mAV;
	}
	
	@PostMapping("/")
	public RedirectView create(@ModelAttribute("producto") ProductoDTO productoDTO) {
		productoService.insertOrUpdate(productoDTO);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@GetMapping("/form")
	public String producto(Model model) {
		model.addAttribute("producto", new ProductoDTO());
		return ViewRouteHelper.PRODUCTO_FORM;
	}
	
	@PostMapping("/new")
	public ModelAndView newproducto(@Valid @ModelAttribute("producto") ProductoDTO producto, BindingResult bindingResult) {
		ModelAndView mV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.PRODUCTO_FORM);
		} else {
			mV.setViewName(ViewRouteHelper.PRODUCTO_NEW);
			mV.addObject("producto", producto);
		}
		return mV;
	}
	
	
}
