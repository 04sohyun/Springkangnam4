package com.oracle.mvc043;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = "1234";
		
		System.out.println("RedirectController studentConfirm Start...");
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		if(id.equals("abc")) {
			return "redirect:studentOk";
		}
		return "redirect:studentErr";
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		System.out.println("RedirectController studentOk Start....");
		
		System.out.println("password->"+password);
		model.addAttribute("id", id);
		model.addAttribute("password", password);
		return "student/studentOk";
	}
	
	@RequestMapping("/studentErr")
	public String studentErr(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		System.out.println("Redirectcontroller studentErr Start...");
		return "student/studentErr";
	}
}
