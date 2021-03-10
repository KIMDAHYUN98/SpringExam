package com.company.temp.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.BankAPI;


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
	
	// userInfo 받기
	@RequestMapping("/userinfo")
	public String userinfo(HttpSession session, HttpServletRequest request) {
		//String access_token = session.getAttribute("access_token");
		//String access_token = request.getParameter("access_token");
		//System.out.println("access_token==============" + access_token);
		String access_token = "";
		String user_num = "";
		Map<String, Object> userinfo = BankApI.getUserinfo(access_token,user_num);
		System.out.println("userinfo=" + userinfo);
		return "home";
	}
}
