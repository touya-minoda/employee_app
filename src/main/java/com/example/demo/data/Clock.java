package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clock {
	
	@JsonProperty("id")
	private int id;

	@JsonProperty("employee_id")
	private int employeeId;

	@JsonProperty("clock_in")
	private String clockIn;
	
	@JsonProperty("clock_out")
	private String clockOut;
	
	@JsonProperty("break_start")
	private String breakStart;

	@JsonProperty("break_end")
	private String breakEnd;
	
}