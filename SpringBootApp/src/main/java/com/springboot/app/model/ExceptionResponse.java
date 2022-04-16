package com.springboot.app.model;

import java.time.LocalDateTime;

/**
 * @author Ashish Gupta
 *
 */

public class ExceptionResponse {

	private String message;
	private LocalDateTime localDateTime;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
}
