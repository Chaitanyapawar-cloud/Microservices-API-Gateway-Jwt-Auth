package com.csp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csp.api.dto.UserLoginDto;
import com.csp.api.entity.UserCredentials;
import com.csp.api.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/saveUser")
	public ResponseEntity<String> saveUser(@RequestBody UserCredentials creds){
		String msg = authService.saveUserDetails(creds);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/token")
	public ResponseEntity<String> getToken(@RequestBody UserLoginDto dto){
		String token = authService.authUserDetails(dto);
		return new ResponseEntity<String>(token,HttpStatus.OK);
	}

}
