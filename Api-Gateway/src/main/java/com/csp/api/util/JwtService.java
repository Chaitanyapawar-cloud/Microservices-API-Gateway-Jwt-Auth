package com.csp.api.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    
	public String generateNewToken(String userName) {
		 Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().
		setClaims(claims)
		.setSubject(userName)
		.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*30))
		.setIssuedAt(new Date())
		.signWith(SignatureAlgorithm.HS256,Base64.getEncoder().encode(SECRET.getBytes()))
		.compact();
		
	}
	
	
	public boolean validateToken(String token) {
		Claims body = Jwts.parserBuilder().setSigningKey(Base64.getEncoder().encode(SECRET.getBytes()))
		.build().parseClaimsJws(token).getBody();
		
		Date exp = body.getExpiration();
		
		return exp.after(new Date());
	}

}
