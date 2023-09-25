package com.example.demo.controller;

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
		
		String jsonBody = "{\"body\": \"{\\\"name\\\":\\\"" + name 
						+ "\\\",\\\"hometown\\\":\\\"" + homeTown
						+ "\\\",\\\"joining_month\\\":\\\"" + joiningMonth
						+ "\\\"}\"}";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
		 
		restTemplate.postForObject(url, entity, String.class);
		
		return "redirect:/top";
	}

}