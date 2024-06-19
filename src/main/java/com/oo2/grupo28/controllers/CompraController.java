package com.oo2.grupo28.controllers;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo28.dtos.CompraDTO;
import com.oo2.grupo28.dtos.ProductoDTO;
import com.oo2.grupo28.entities.Compra;
import com.oo2.grupo28.entities.Producto;
import com.oo2.grupo28.entities.User;
import com.oo2.grupo28.helpers.ViewRouteHelper;
import com.oo2.grupo28.services.ICompraService;
import com.oo2.grupo28.services.IProductoService;
import com.oo2.grupo28.services.implementation.UserService;



@Controller
@RequestMapping("/compras")
public class CompraController {
	
	private ICompraService compraService;
	private IProductoService productoService;
	private UserService userService;

	
	public CompraController(ICompraService compraService,IProductoService productoService,UserService userService) {
		super();
		this.compraService = compraService;
		this.productoService = productoService;
		this.userService = userService;
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_INDEX);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mAV.addObject("username", user.getUsername());
		mAV.addObject("productos", productoService.getAll());
		return mAV;
	}
	
	
	@PostMapping("/comprar")
    public String comprarProducto(@RequestParam("productoId") int productoId, @RequestParam("cantidad") int cantidad, @AuthenticationPrincipal UserDetails userDetails, Model model) throws RuntimeException, Exception {
        Producto producto = productoService.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        if (producto.getStock().getCantidadActual() < cantidad) {
            // Añadir un mensaje de error al modelo
            model.addAttribute("error", "No hay suficiente stock para el producto: " + producto.getNombre());
            model.addAttribute("productos", productoService.getAll());
            model.addAttribute("username", userDetails.getUsername());
            return ViewRouteHelper.USER_INDEX;  // Redirige de vuelta a la página de inicio
        }
        
        // Crear la compra
        Compra compra = new Compra();
        compra.setFechaCompra(LocalDate.now());
        compra.setCantidadComprada(cantidad);
        compra.setPrecioVenta(producto.getPrecio());
        compra.setProducto(producto);
        compra.setUser(userService.findByUsernameAndFetchUserRolesEagerly(userDetails.getUsername()));
        compraService.insert(compra);

        // Añadir un mensaje de éxito al modelo
        model.addAttribute("success", "Compra realizada con éxito para el producto: " + producto.getNombre());
        model.addAttribute("productos", productoService.getAll());
        model.addAttribute("username", userDetails.getUsername());
        return ViewRouteHelper.USER_INDEX;
    }
	
	
	
	

}
