package com.oo2.grupo28.controllers;

import java.time.LocalDate;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo28.helpers.ViewRouteHelper;
import com.oo2.grupo28.services.ILoteService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/reportes")
public class ReporteController {
	
	private ILoteService loteService;
	

	public ReporteController(ILoteService loteService) {
		super();
		this.loteService = loteService;
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.REPORTE_INDEX);
		return mAV;
	}
	
	@GetMapping("/lote")
	public ModelAndView informeLote() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.REPORTE_LOTE);
		mAV.addObject("lotes", loteService.getAll());
		return mAV;
	}
	
	@GetMapping("/lote/{fecha}")
	public ModelAndView loteFecha(@PathVariable("fecha") LocalDate fecha) throws Exception {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.REPORTE_LOTE);
		mAV.addObject("lotes", loteService.findByFecha(fecha));
		return mAV;
	}
	
}
