package com.example.demo.controller;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller

public class CreateController {
	
	//private final EmployeeService employeeService;
	
	//public CreateController(EmployeeService employeeService) {
	//	this.employeeService = employeeService;
	//}
	
	@GetMapping("create")
	public String create() {
		return "create.html";
	}
	
	//新規社員登録
	@PostMapping("create")
	public String newEmployee(@RequestParam("name") String name, @RequestParam("hometown") String homeTown, @RequestParam("joiningMonth") String joiningMonth, Model model) throws IOException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";
		
		//リクエスト情報の作成
//		Register register = new Register();
//		register.setName(name);
//		register.setHometown(homeTown);
//		register.setJoiningMonth(joiningMonth);
		
		//JSON形式に変換
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonBody = objectMapper.writeValueAsString(register);
		
		String jsonBody = "{\"body\": \"{\\\"name\\\":\\\"" + name 
						+ "\\\",\\\"hometown\\\":\\\"" + homeTown
						+ "\\\",\\\"joining_month\\\":\\\"" + joiningMonth
						+ "\\\"}\"}";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
		 
		restTemplate.postForObject(url, entity, String.class);
		//String responseBody = responseEntity.getBody();
		
//		model.addAttribute("register", response);
	
		//model.addAttribute("register", employee.getName() +"　"+ employee.getHometown());
		
		//return "redirect:/top";
		return "create.html";
	}

}