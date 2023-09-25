package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {
	@JsonProperty("name")
	private String name;

	@JsonProperty("hometown")
	private String hometown;
	
	@JsonProperty("joining_month")
	private String joiningMonth;
}