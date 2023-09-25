package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.data.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class TopController {
	
	private final EmployeeService employeeService;
	
	public TopController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("top")
	public String employeeList(Model model) throws IOException {
		
		List<Employee> employeeList = employeeService.allEmployee();
		
		model.addAttribute("employeeList", employeeList);
		
		return "index.html";
	}

}