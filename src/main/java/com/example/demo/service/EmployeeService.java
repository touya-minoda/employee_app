package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.data.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> allEmployee() throws IOException {

		Employee[] employeeList = employeeRepository.allEmployee();

		return Arrays.asList(employeeList);

	}
	
	public List<Employee> detailEmployee(int id) throws IOException {

		Employee[] employeeList = employeeRepository.detailEmployee(id);

		return Arrays.asList(employeeList);

	}
	
	//社員新規登録
	public void createEmployee(String name, String homeTown, String joiningMonth) throws IOException {
		//リポジトリを呼び出し
		employeeRepository.createEmployee(name, homeTown, joiningMonth);
		
	}
	
}