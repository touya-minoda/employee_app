package com.example.demo.repository;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.Clock;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ClockRepository {
	
	//社員勤怠情報を取得(GET)
	public Clock[] getClock(int id) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock?employeeId={id}";

		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, null, String.class, id);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Clock[] clockList = mapper.readValue(json, Clock[].class);

		return clockList;
	}
	
	//勤怠情報を登録(POST)
	public void clockRegister(String jsonBody) throws IOException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
		 
		restTemplate.postForObject(url, entity, String.class);
	}
}