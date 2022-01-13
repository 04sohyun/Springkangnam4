package com.oracle.oBootHello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootHello.domain.Emp;


@Controller
public class HelloController {
	

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	
	@RequestMapping("/hello")
	public String hello(Model model) {
		logger.info("hello Start");
		
		model.addAttribute("hdata", "hello Start");
		
		return "hello";
	}
	
	@RequestMapping("ajaxString")
	@ResponseBody
	public String ajaxString(@RequestParam("ajaxName") String aName) {
		System.out.println("HelloController ajaxString aName  :  " +aName);
		
		return aName;
	}
	
	@RequestMapping("ajaxEmp")
	@ResponseBody
	public Emp ajaxEmp(@RequestParam("empno") String empno, @RequestParam("ename") String ename) {
		System.out.println("HelloController ajaxString empno  :  " +empno);
		
		logger.info("empno"+empno);
		
		Emp emp = new Emp();
		emp.setEmpno(empno);
		emp.setEmpno(ename);
		
		return emp;
	}
	
}
