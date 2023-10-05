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
	
	//社員詳細ページ勤怠情報登録(POST)
	@PostMapping("/clock")
	public String clockIn(@RequestParam("employeeId") int employeeId, @RequestParam("clock") String clock, Model model) throws IOException {
		//従業員IDとボタンのタイプを取得し、サービスクラスに送る
		clockService.register(employeeId, clock);
		
		model.addAttribute("kinds", clock);
		
		return "redirect:/top";

	}
}