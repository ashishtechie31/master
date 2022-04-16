package com.springboot.app.util;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ashish Gupta
 *
 */
@Component
@EnableWebSecurity
public class CorsWebSecurityConfig implements WebMvcConfigurer  {
	
	public void addcorsMapping(CorsRegistry corsRegistry) {		
		corsRegistry.addMapping("/**").allowedOrigins("http://localhost:8070").
		allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowCredentials(true).maxAge(3600);
		}

		
	}

