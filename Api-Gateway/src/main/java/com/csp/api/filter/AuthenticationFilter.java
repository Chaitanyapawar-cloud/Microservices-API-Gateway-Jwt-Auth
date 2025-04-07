package com.csp.api.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.csp.api.util.JwtService;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
    @Autowired
    private Validator validator;
    
	@Autowired
	private JwtService jwtService;
	
	  public AuthenticationFilter() {
	        super(Config.class);
	    }

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange,chain)->{
			
			if(validator.isSecured(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					  throw new RuntimeException("missing authorization header");
				}
				
				String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				token = token.substring(7);
				
				if(jwtService.validateToken(token)) {
					
				}else {
					System.out.println("invalid access...!");
					throw new RuntimeException("un authorized access to application");
				}
			}
			
			return chain.filter(exchange);
		});
	}

	public static class Config {
		
	}

}
