package com.springboot.app.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Ashish Gupta
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 5765716013124791586L;
	public static final long JWT_TOKEN_VALIDITY= 5*60*60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token,Function<Claims, T> claimsResolver) {
		final Claims claim  = getAllClaimsFromToken(token);		
		return claimsResolver.apply(claim);		
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
	}
	
	private boolean isTokenExpired(String token) {
		 final Date expiryDate = getExpirationDateFromToken(token);
		 return expiryDate.before(new Date());	
	}
	
	public String generateToken(UserDetails userDetails)
	{
		Map<String,Object> claims = new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String,Object> claims,String subject)
	{
		
		String token = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).
				setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
		System.out.println("JwtTokenUtil.doGenerateToken() "+token);
		return token;
	}
	
	public boolean validateToken(String token,UserDetails userDetails)
	{
		final String userName =  getUsernameFromToken(token);
		return userName.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);		
	}
}
