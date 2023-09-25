package com.example.demo.repository;

import java.io.IOException;
// import java.time.Clock;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.Clock;
import com.example.demo.data.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ClockRepository {
	
	//社員勤怠情報を取得(GET)
	public Clock[] getClock(int id) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock?employeeId={id}";
		//String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";

		RestTemplate rest = new RestTemplate();
		
		//int id = 1;
		//ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, null, String.class, id);
		ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, null, String.class, id);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Clock[] clockList = mapper.readValue(json, Clock[].class);

		return clockList;
	}
	
	//社員の詳細情報を取得
	public Employee[] detailEmployee(int id) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee?id={id}";

		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, null, String.class, id);
		//ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, null, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employee[] employeeList = mapper.readValue(json, Employee[].class);

		return employeeList;
	}
	
	


}