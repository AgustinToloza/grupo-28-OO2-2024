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
import com.oo2.grupo28.dtos.ProductoDTO;
import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.services.IProductoService;

import jakarta.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/productos")
public class ProductoController {
	
	private IProductoService productoService;
	
	private ModelMapper modelMapper = new ModelMapper();

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
	
	@GetMapping("/new")
	public String producto(Model model) {
		model.addAttribute("producto", new ProductoDTO());
		return ViewRouteHelper.PRODUCTO_NEW;
	}
	
	@PostMapping("/new")
	public RedirectView create(@ModelAttribute("producto") ProductoDTO productoDTO) {
		Producto producto = modelMapper.map(productoDTO, Producto.class);
		productoService.insertOrUpdate(producto);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView get(@PathVariable("id") int id) throws Exception {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_UPDATE);
		ProductoDTO productoDTO = modelMapper.map(productoService.findById(id).get(), ProductoDTO.class);
		mAV.addObject("producto", productoDTO);
		return mAV;
	}
	
	@PostMapping("/update/{id}")
	public RedirectView update(@PathVariable("id") int id, @ModelAttribute("producto") ProductoDTO productoDTO) throws Exception {
		Producto productoToUpdate = modelMapper.map(productoService.findById(id).get(), Producto.class);
		if(productoToUpdate != null ) {
			productoToUpdate.setId(productoDTO.getId());
			productoToUpdate.setNombre(productoDTO.getNombre());
			productoToUpdate.setPrecio(productoDTO.getPrecio());
			productoToUpdate.setCodigo(productoDTO.getCodigo());
			productoToUpdate.setActivo(productoDTO.isActivo());
			productoService.insertOrUpdate(productoToUpdate);
		}
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		productoService.remove(id);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@PostMapping("/new/estenova")
	public ModelAndView newproducto(@Valid @ModelAttribute("producto") ProductoDTO producto, BindingResult bindingResult) {
		ModelAndView mV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.PRODUCTO_NEW);
		} else {
			mV.setViewName(ViewRouteHelper.PRODUCTO_NEW);
			mV.addObject("producto", producto);
		}
		return mV;
	}
}



