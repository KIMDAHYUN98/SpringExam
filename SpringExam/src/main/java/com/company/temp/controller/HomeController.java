package com.company.temp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}

	// 페이지 이동
	@GetMapping("/getBiz")
	public String getBizForm() {
		return "getBiz";
	}
	
	// 크롤링결과 조회
	@PostMapping("/getBiz") // VO, Map, String 모두 가능
	public String getBiz(@RequestParam String bizno, Model model) throws Exception { // name 값과 변수명이 같아야 한다. 파라미터가 하나뿐이라면 String으로 받아도 된다.
		// 크롤링
		String url = "https://bizno.net/?query=" + bizno;
		Document doc = Jsoup.connect(url).get();
		Elements element = doc.select(".titles a h4");
		String bizName = element.text();
		System.out.println(bizName);
		
		// 모델에 저장해서 뷰페이지로 이동한다, 회사명 찾아서 리턴한다.
		model.addAttribute("bizname", bizName);

		return "getBiz";
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
