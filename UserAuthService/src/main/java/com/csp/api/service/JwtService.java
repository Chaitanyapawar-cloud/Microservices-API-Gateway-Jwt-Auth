package com.csp.api.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

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
		.signWith(getSignKey(),SignatureAlgorithm.HS256)
		.compact();
		
	}
    
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
