package com.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.JwtRequest;
import com.springboot.app.model.JwtResponse;
import com.springboot.app.service.JwtUserDetailService;
import com.springboot.app.util.JwtTokenUtil;

/**
 * @author Ashish Gupta
 * https://www.javainuse.com/spring/boot-jwt
 * https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 */
@RestController
@CrossOrigin
public class JWTController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailService userDetailService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		String token =  jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}


	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException de) {
			throw new Exception("USER_DISABLED",de);
		}
		catch(BadCredentialsException be) {
			throw new Exception("INVALID_CREDENTIALS",be);
		}
		
	}
	
}
