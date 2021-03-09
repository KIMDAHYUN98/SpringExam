package com.company.temp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.temp.service.ProductService;
import com.company.temp.vo.ProductVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@Autowired ProductService proService;
	
	@GetMapping("/insertPro")
	public String insertPro(ProductVO vo, Model model) {
		return "insertPro";
	}
	
	@PostMapping("/insertPro")
	public String insertProc(ProductVO vo) {
		proService.insertPro(vo);
		return "redirect:/getSearchPro";
	}
	@GetMapping("/getSearchPro")
	public String getSearchPro(ProductVO vo, Model model) {
		model.addAttribute("list", proService.getSearchPro(vo));
		return "getSearchPro";
	}
	
}
