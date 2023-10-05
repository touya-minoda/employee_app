package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.EmployeeService;

@Controller

public class CreateController {
	private final EmployeeService employeeService;
	
	public CreateController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("create")
	public String create() {
		return "create.html";
	}
	
	//新規社員登録
	@PostMapping("create")
	public String newEmployee(@RequestParam("name") String name, @RequestParam("hometown") String homeTown, @RequestParam("joiningMonth") String joiningMonth, Model model) throws IOException {
		//フォームから受け取った値をサービスクラスに渡す
		employeeService.createEmployee(name, homeTown, joiningMonth);
		
		return "redirect:/top";
		
	}
}