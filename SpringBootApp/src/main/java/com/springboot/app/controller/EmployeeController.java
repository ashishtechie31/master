package com.springboot.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

	//localhost:8070/employees/employee/Ashish
	@GetMapping(value="/employee/{name}")
	@CrossOrigin(origins="http://corsdomain:8090")
	public String getData(@PathVariable String name){
		
		return "Hello "+name;
	}
}
