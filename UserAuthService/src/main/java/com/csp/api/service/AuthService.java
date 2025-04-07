package com.csp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csp.api.dto.UserLoginDto;
import com.csp.api.entity.UserCredentials;
import com.csp.api.repo.UserCredentialsRepo;

@Service
public class AuthService {
	
	@Autowired
	private UserCredentialsRepo userCredRepo;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public String saveUserDetails(UserCredentials creds) {
		 creds.setPassword(passwordEncoder.encode(creds.getPassword()));
		 creds = userCredRepo.save(creds);
		return "User resgisted successfully id: "+creds.getId();
	}

	public String authUserDetails(UserLoginDto dto) {
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
		
		if(authenticate.isAuthenticated()) {
			
			return jwtService.generateNewToken(dto.getUserName());
		}else {
			throw new RuntimeException("UnAuthorized User");
		}
	}

}
