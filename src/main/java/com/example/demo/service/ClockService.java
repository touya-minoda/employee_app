package com.example.demo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.Clock;
import com.example.demo.repository.ClockRepository;

@Service
public class ClockService {

	private final ClockRepository clockRepository;

	public ClockService(ClockRepository clockRepository) {
		this.clockRepository = clockRepository;
	}

	public List<Clock> getClock(int id) throws IOException {

		Clock[] clockList = clockRepository.getClock(id);

		return Arrays.asList(clockList);

	}
	
	public void register(int id, String kinds) {
		LocalDateTime nowDate = LocalDateTime.now();
		
		DateTimeFormatter now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // ①
        String formatNowDate = now.format(nowDate);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock"; 
		
		String clockIn = "";
		String breakStart = "";
		String breakEnd = "";
		String clockOut = "";
		
		if(kinds == "clockIn") {
			clockIn = formatNowDate;
		} else if (kinds == "breakStart") {
			breakStart = formatNowDate;
		} else if (kinds == "breakEnd") {
			breakEnd = formatNowDate;
		} else if (kinds == "clockOut") {
			clockOut = formatNowDate;
		}
		
		String jsonBody = "{\"body\": \"{\\\"employee_id\\\":\\\"" + id 
							+ "\\\",\\\"clock_in\\\":\\\"" + clockIn 
							+ "\\\",\\\"break_start\\\":\\\"" + breakStart 
							+ "\\\",\\\"break_end\\\":\\\"" + breakEnd 
							+ "\\\",\\\"clock_out\\\":\\\"" + clockOut + "\\\"}\"}";				
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
		 
		restTemplate.postForObject(url, entity, String.class);
	}
	
}