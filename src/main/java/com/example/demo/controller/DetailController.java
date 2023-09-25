package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.Clock;
import com.example.demo.data.Employee;
import com.example.demo.service.ClockService;
import com.example.demo.service.EmployeeService;

@Controller
public class DetailController {
	
	private final EmployeeService employeeService;
	private final ClockService clockService;
	
	public DetailController(EmployeeService employeeService, ClockService clockService) {
		this.employeeService = employeeService;
		this.clockService = clockService;
	}
	
	
	@GetMapping("detail")
	public String getClock(@RequestParam("id") int id, Model model) throws IOException {
		
		List<Employee> employeeList = employeeService.detailEmployee(id);
		
		List<Clock> clockList = clockService.getClock(id);
		
		model.addAttribute("employeeName", employeeList.get(0).getName());
		model.addAttribute("clockList", clockList);
		
		int employeeId = id;
		
		model.addAttribute("employeeId", employeeId);
		
		return "detail.html";
	}
	
	@PostMapping("/clockIn")
	public String clockIn(@RequestParam("employeeId") int id, Model model) throws IOException {
		String kinds = "clockIn";
		clockService.register(id, kinds);
		return "redirect:/top";
	}
	
	@PostMapping("/breakStart")
	public String breakStart(@RequestParam("employeeId") int id, Model model) throws IOException {
		String kinds = "breakStart";
		clockService.register(id, kinds);
		return "redirect:/top";
	}
	
	@PostMapping("/breakEnd")
	public String breakEnd(@RequestParam("employeeId") int id, Model model) throws IOException {
		String kinds = "breakEnd";
		clockService.register(id, kinds);
		return "redirect:/top";
	}
	
	@PostMapping("/clockOut")
	public String clockOut(@RequestParam("employeeId") int id, Model model) throws IOException {
		String kinds = "clockOut";
		clockService.register(id, kinds);
		return "redirect:/top";
	}

}