package com.oo2.grupo28.controllers;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo28.helpers.ViewRouteHelper;
import com.oo2.grupo28.services.IProductoService;

@Controller
@RequestMapping("/home")
public class HomeController {
		
		private IProductoService productoService;
		
		public HomeController(IProductoService productoService) {
			super();
			this.productoService = productoService;
		}

		//GET Example: SERVER/index
		@GetMapping("/index")
		public ModelAndView index() {
			
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			ModelAndView modelAndView = new ModelAndView();
			
			for (GrantedAuthority authority : authorities) {
	            if (authority.getAuthority().equals("ROLE_ADMIN")) {
	            	
	            	modelAndView.setViewName(ViewRouteHelper.ADMIN_INDEX);
	    			modelAndView.addObject("username", user.getUsername());
	    			
	            } else if (authority.getAuthority().equals("ROLE_USER")) {
	            	
	            	modelAndView.setViewName(ViewRouteHelper.USER_INDEX);
	    			modelAndView.addObject("username", user.getUsername());
	    			modelAndView.addObject("productos", productoService.getAll());
	            }
	        }
			
			return modelAndView;
			
		}

		/*GET Example: SERVER/hello?name=someName
		@GetMapping("/hello")
		public ModelAndView helloParams1(@RequestParam(name="name", required=false, defaultValue="null") String name) {
			ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
			mV.addObject("name", name);
			return mV;
		}

		//GET Example: SERVER/hello/someName
		@GetMapping("/hello/{name}")
		public ModelAndView helloParams2(@PathVariable("name") String name) {
			ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
			mV.addObject("name", name);
			return mV;
		}*/

		@GetMapping("/")
		public RedirectView redirectToHomeIndex() {
			return new RedirectView(ViewRouteHelper.ROUTE);
		}
}
