package com.company.temp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.BankAPI;
import com.company.BankVO;


@Controller
public class BankController {
	
	@Autowired BankAPI BankApI;
	@RequestMapping("/auth")
	public String auth() throws Exception {
		String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/authorize";
		String response_type = "code";
		String client_id = "de1e3afe-a852-40ca-a741-5658dafd24b1";
		String redirect_uri = "http://localhost/temp/callback";
		String scope = "login inquiry transfer";
		String state = "01234567890123456789012345678901";
		String auth_type = "0";
		
					  
		StringBuilder qstr = new StringBuilder();
		qstr.append("response_type=" + response_type)
			.append("&client_id=" + client_id)
			.append("&redirect_uri=" + redirect_uri)
			.append("&scope=" + scope)
			.append("&state=" + state)
			.append("&auth_type=" + auth_type);
		
		return "redirect:" + reqURL + "?" + qstr.toString();
}
	
	@RequestMapping("/callback")
	public String callback(@RequestParam Map<String, Object> map, HttpSession session) {
		System.out.println("==============" + map.get("code"));
		String code = (String) map.get("code");
		// access_token 받기
		String access_token = BankApI.getAccessToken(code);
		System.out.println("access_token=" + access_token);
		
		// 섹션에 담기
		session.setAttribute("access_token", access_token);
		return "home";
	}
	
	String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzcwNTI4Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MjMxNDMxNzcsImp0aSI6ImY4MTFhNDhiLTYxY2UtNDFmOC1hOTI4LTU5MWZhMDM2M2UzNyJ9.Clu2rCwQ0Y4f9zCi51JXbftH8_tmad2sVGoSf7BKDc8";
	// userInfo 받기
	@RequestMapping("/getAccoutList")
	public String userinfo(HttpServletRequest request, Model model) {
		String user_num = "1100770528";
		Map<String, Object> map = BankApI.getAccoutList(access_token,user_num);
		System.out.println("getAccoutList=" + map);
		model.addAttribute("list", map);
		return "bank/getAccoutList";
	}
	
	@RequestMapping("/getBalance") // jsp로 이동
	public String getBalance(BankVO vo, Model model) {
		vo.setAccess_token(access_token);
		HashMap<String, Object> map = BankApI.getBalance(vo);
		System.out.println("잔액=" + map);
		model.addAttribute("balance", map);
		return "bank/getBalance"; // 완성된 뷰페이지를 넘긴다.
	}
	@ResponseBody
	@RequestMapping("/ajaxGetBalance") // data만 넘어감
	public Map ajaxGetBalance(BankVO vo) {
		vo.setAccess_token(access_token);
		HashMap<String, Object> map = BankApI.getBalance(vo);
		System.out.println("잔액=" + map);
		return map; 
	}
	
	@RequestMapping("/getOrgAuthorize")
	public String getOrgAuthorize() {
		Map<String, Object> map = BankApI.getOrgAccessToken(); // 토큰 받은 값을 출력
		System.out.println("access_token : " + map.get("access_token"));
		return "home";
	}
}
