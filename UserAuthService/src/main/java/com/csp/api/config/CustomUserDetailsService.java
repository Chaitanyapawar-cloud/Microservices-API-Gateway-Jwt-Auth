package com.csp.api.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.csp.api.entity.UserCredentials;
import com.csp.api.repo.UserCredentialsRepo;



@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	public UserCredentialsRepo credRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredentials> dto = credRepo.findByUserName(username);
		
		if(!dto.isPresent()) {
			throw new RuntimeException("Invalid Credentials");
		}
		return dto.map(CustomUser::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
	}

}
