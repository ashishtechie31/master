package com.springboot.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.exception.CustomException;

/**
 * @author Ashish Gupta
 *
 */
@RestController
@RequestMapping(path="/departmentDetails")
public class DepartmentController {

	//localhost:8070/departmentDetails/deptName/Education
	//https://spring.io/guides/gs/spring-cloud-loadbalancer/
	@GetMapping(value="/deptName/{deptName}")
	//@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	public String getServiceDetails(@PathVariable String deptName) {
		
		if(deptName.contains("E")){
			
			throw new CustomException();
		}
						
		return "Department "+deptName;
		
	}
}
