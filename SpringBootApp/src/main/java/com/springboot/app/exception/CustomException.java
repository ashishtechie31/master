
package com.springboot.app.exception;

/**
 * @author Ashish Gupta
 *
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 4333830497643971688L;

	private String message;
	
		public CustomException() {
			super();
		}
		
		public CustomException(String message) {
			super(message);
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
}
