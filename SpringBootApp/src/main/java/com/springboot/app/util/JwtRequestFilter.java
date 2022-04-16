package com.springboot.app.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.app.service.JwtUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailService JwtUserDetailService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
			final String requestTokenHeader  = request.getHeader("Authorization");
			System.out.println("requestTokenHeader "+requestTokenHeader);
			String username=null;
			String jwtToken = null;
			if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
			{
				jwtToken =  requestTokenHeader.substring(7);
				try {
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);					
				}
				catch (IllegalArgumentException ille) {
					System.out.println("Unable to get Jwt Token."+ille.getLocalizedMessage());
				}
				catch(ExpiredJwtException e) {
					System.out.println("Jwt Token Expired."+e.getLocalizedMessage());
				}				
			}
			else {
				logger.warn("Jwt Token doesn't start with Bearer String");
			}
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails =  this.JwtUserDetailService.loadUserByUsername(username);
				if(jwtTokenUtil.validateToken(jwtToken, userDetails))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken  = new 
							UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			System.out.println("jwtToken "+jwtToken);
			filterChain.doFilter(request, response);
	}

	public JwtUserDetailService getJwtUserDetailService() {
		return JwtUserDetailService;
	}

	public void setJwtUserDetailService(JwtUserDetailService jwtUserDetailService) {
		JwtUserDetailService = jwtUserDetailService;
	}

	public JwtTokenUtil getJwtTokenUtil() {
		return jwtTokenUtil;
	}

	public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
	}

	
}
