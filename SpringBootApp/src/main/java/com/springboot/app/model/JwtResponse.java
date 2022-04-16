/**
 * 
 */
package com.springboot.app.model;

import java.io.Serializable;

/**
 * @author Ashish Gupta
 *
 */
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 3837816449226271010L;
	
	private String token;

	public JwtResponse() {
	}
	
	public JwtResponse(String token) {
		this.token=token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
