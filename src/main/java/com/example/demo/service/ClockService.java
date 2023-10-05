package com.example.demo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

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
	
	//勤怠情報登録
	public void register(int id, String kinds) throws IOException {
		LocalDateTime nowDate = LocalDateTime.now();
		
		DateTimeFormatter now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        String formatNowDate = now.format(nowDate); 
		
		String clockIn = "";
		String breakStart = "";
		String breakEnd = "";
		String clockOut = "";
		
		if(kinds.equals("clockIn")) {
			clockIn = formatNowDate;
		} else if (kinds.equals("breakStart")) {
			breakStart = formatNowDate;
		} else if (kinds.equals("breakEnd")) {
			breakEnd = formatNowDate;
		} else if (kinds.equals("clockOut")) {
			clockOut = formatNowDate;
		}
		
		String jsonBody = "{\"body\": \"{\\\"employee_id\\\":\\\"" + id 
							+ "\\\",\\\"clock_in\\\":\\\"" + clockIn 
							+ "\\\",\\\"break_start\\\":\\\"" + breakStart 
							+ "\\\",\\\"break_end\\\":\\\"" + breakEnd 
							+ "\\\",\\\"clock_out\\\":\\\"" + clockOut + "\\\"}\"}";				
		
		clockRepository.clockRegister(jsonBody);
	}
	
}