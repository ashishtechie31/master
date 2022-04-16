/**
 * 
 */
package com.springboot.app.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author Ashish Gupta
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,Serializable {

	private static final long serialVersionUID = 6252180128659129696L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException)  {
		try {
		System.out.println("JwtAuthenticationEntryPoint");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
