package com.springboot.app.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.app.exception.CustomException;
import com.springboot.app.model.ExceptionResponse;

/**
 * @author Ashish Gupta
 * https://javatodev.com/microservices-exception-handling/
 */
@ControllerAdvice
public class CustomExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value=CustomException.class)
	protected ResponseEntity<Object> handleAllException(CustomException customException, WebRequest webRequest) {
		
		System.out.println("ExceptionController.handleAllException()");
		ExceptionResponse response =  new ExceptionResponse();
		response.setLocalDateTime(LocalDateTime.now());
		response.setMessage("Exception Occured"); // make it custom.
		return new ResponseEntity<>(response.getMessage()+" "+response.getLocalDateTime(),HttpStatus.NOT_FOUND);			
	}
}
